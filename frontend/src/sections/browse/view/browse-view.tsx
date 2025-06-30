import { useState, useCallback } from 'react';

import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Pagination from '@mui/material/Pagination';

import { _songs } from 'src/_mock';
import { DashboardContent } from 'src/layouts/dashboard';

import { BrowseItem } from '../browse-item';
import { BrowseSort } from '../browse-sort';
import { BrowseSearch } from '../browse-search';
import { BrowseFilters } from '../browse-filters';

import type { IBrowseItem } from '../browse-item';
import type { FiltersProps } from '../browse-filters';
// ----------------------------------------------------------------------

function isEqualArray(a: string[], b: string[]) {
  if (a.length !== b.length) return false;
  return a.every((val, idx) => val === b[idx]);
}

type Props = {
  browses: IBrowseItem[];
};

const STATUS_OPTIONS = [
  { value: 'pending', label: 'Pending' },
  { value: 'verified', label: 'Verified' },
  { value: 'archived', label: 'Archived' },
];

const GENRE_OPTIONS = [
  { value: 'classical', label: 'Classical' },
  { value: 'pop', label: 'Pop' },
  { value: 'rock', label: 'Rock' },
  { value: 'jazz', label: 'Jazz' },
];

const defaultFilters: FiltersProps = {
  genre: [],
  status: [],
};

export function BrowseView({ browses }: Props) {
  const [songs, setSongs] = useState(_songs);

  const [searchQuery, setSearchQuery] = useState('');

  // console.log('songs', songs);
  const [sortBy, setSortBy] = useState('featured');

  const [openFilter, setOpenFilter] = useState(false);

  const [filters, setFilters] = useState<FiltersProps>(defaultFilters);

  const filteredSongs = songs
    .filter((song) => {
      if (searchQuery && !song.title.toLowerCase().includes(searchQuery.toLowerCase())) {
        return false;
      }
      if (filters.status.length > 0 && !filters.status.includes(song.status)) {
        return false;
      }
      if (filters.genre.length > 0 && !filters.genre.includes(song.genre)) {
        return false;
      }
      return true;
    })
    .sort((a, b) => {
      switch (sortBy) {
        case 'newest':
          return new Date(b.postedAt).getTime() - new Date(a.postedAt).getTime();
        case 'mostViewed':
          return (b.totalViews || 0) - (a.totalViews || 0);
        case 'leastViewed':
          return (a.totalViews || 0) - (b.totalViews || 0);
        case 'mostFavorited':
          return (b.totalFavorites || 0) - (a.totalFavorites || 0);
        case 'leastFavorited':
          return (a.totalFavorites || 0) - (b.totalFavorites || 0);
        case 'featured':
        default:
          return 0;
      }
    });
  
  const handleSearch = useCallback((query: string) => {
    setSearchQuery(query);
  }, []);

  const handleOpenFilter = useCallback(() => {
    setOpenFilter(true);
  }, []);
  console.log(filters);

  const handleCloseFilter = useCallback(() => {
    setOpenFilter(false);
  }, []);

  const handleSort = useCallback((newSort: string) => {
    setSortBy(newSort);
  }, []);

  const handleSetFilters = useCallback((updateState: Partial<FiltersProps>) => {
    setFilters((prevValue) => ({ ...prevValue, ...updateState }));
  }, []);

  const canReset = Object.keys(filters).some((key) => {
    const filterValue = filters[key as keyof FiltersProps];
    const defaultValue = defaultFilters[key as keyof FiltersProps];

    if (Array.isArray(filterValue) && Array.isArray(defaultValue)) {
      return !isEqualArray(filterValue, defaultValue);
    }
    return filterValue !== defaultValue;
  });

  return (
    <DashboardContent>
      <Box
        sx={{
          mb: 5,
          display: 'flex',
          alignItems: 'center',
        }}
      >
        <Typography variant="h4" sx={{ flexGrow: 1 }}>
          Browse Songs
        </Typography>
      </Box>

      <Box
        sx={{
          mb: 5,
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
        }}
      >
        <BrowseSearch songs={songs} handleSearch={handleSearch} />
        <Box
          sx={{
            display: 'flex',
            alignItems: 'center',
            gap: 2,
          }}
        >
          <BrowseFilters
            canReset={canReset}
            filters={filters}
            onSetFilters={handleSetFilters}
            openFilter={openFilter}
            onOpenFilter={handleOpenFilter}
            onCloseFilter={handleCloseFilter}
            onResetFilter={() => setFilters(defaultFilters)}
            options={{
              statuses: STATUS_OPTIONS,
              categories: GENRE_OPTIONS,
            }}
          />

          <BrowseSort
            sortBy={sortBy}
            onSort={handleSort}
            options={[
              { value: 'featured', label: 'Featured' },
              { value: 'newest', label: 'Newest' },
              { value: 'mostViewed', label: 'Most viewed' },
              { value: 'leastViewed', label: 'Least viewed' },
              { value: 'mostFavorited', label: 'Most favorited' },
              { value: 'leastFavorited', label: 'Least favorited' },
            ]}
          />
        </Box>
      </Box>


      <Grid container spacing={3}>
        {filteredSongs.map((song, index) => {
          const latestSongLarge = index === 0;
          const latestSong = index === 1 || index === 2;

          return (
            <Grid
              key={song.id}
              size={{
                xs: 12,
                sm: latestSongLarge ? 12 : 6,
                md: latestSongLarge ? 6 : 3,
              }}
            >
              <BrowseItem song={song} latestSong={latestSong} latestSongLarge={latestSongLarge} />
            </Grid>
          );
        })}
      </Grid>

      <Pagination count={10} color="primary" sx={{ mt: 8, mx: 'auto' }} />
    </DashboardContent>
  );
}
