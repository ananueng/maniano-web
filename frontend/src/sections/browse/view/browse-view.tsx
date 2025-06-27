import { useState, useCallback } from 'react';

import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Pagination from '@mui/material/Pagination';

import { DashboardContent } from 'src/layouts/dashboard';

import { BrowseItem } from '../browse-item';
import { BrowseSort } from '../browse-sort';
import { BrowseSearch } from '../browse-search';
import { BrowseFilters } from '../browse-filters';

import type { IBrowseItem } from '../browse-item';
import type { FiltersProps} from '../browse-filters';
// ----------------------------------------------------------------------

type Props = {
  browses: IBrowseItem[];
};

const ARTIST_OPTIONS = [
  { value: 'all', label: 'All Artists' },
  { value: 'artist1', label: 'Artist 1' },
  { value: 'artist2', label: 'Artist 2' },
];

const GENRE_OPTIONS = [
  { value: 'all', label: 'All' },
  { value: 'classical', label: 'Classical' },
  { value: 'pop', label: 'Pop' },
  { value: 'rock', label: 'Rock' },
  { value: 'jazz', label: 'Jazz' },
];

const IS_PUBLIC_OPTIONS = [
  { value: '', label: 'All' },
  { value: 'true', label: 'Public' },
  { value: 'false', label: 'Private' },
];

const defaultFilters: FiltersProps = {
  artist: [],
  isPublic: '',
  minViews: 0,
  minFavorites: 0,
  genre: 'all',
};

export function BrowseView({ browses }: Props) {
  const [sortBy, setSortBy] = useState('featured');

  const [openFilter, setOpenFilter] = useState(false);

  const [filters, setFilters] = useState<FiltersProps>(defaultFilters);

  const handleOpenFilter = useCallback(() => {
    setOpenFilter(true);
  }, []);

  const handleCloseFilter = useCallback(() => {
    setOpenFilter(false);
  }, []);

  const handleSort = useCallback((newSort: string) => {
    setSortBy(newSort);
  }, []);

  const handleSetFilters = useCallback((updateState: Partial<FiltersProps>) => {
    setFilters((prevValue) => ({ ...prevValue, ...updateState }));
  }, []);

  const canReset = Object.keys(filters).some(
    (key) => filters[key as keyof FiltersProps] !== defaultFilters[key as keyof FiltersProps]
  );


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
          Song Listing
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
        <BrowseSearch songs={browses} />
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
              artists: ARTIST_OPTIONS,
              categories: GENRE_OPTIONS,
              isPublic: IS_PUBLIC_OPTIONS,
            }}
          />

          <BrowseSort
            sortBy={sortBy}
            onSort={handleSort}
            options={[
              { value: 'featured', label: 'Featured' },
              { value: 'newest', label: 'Newest' },
              { value: 'mostViewed', label: 'Most Viewed' },
              { value: 'leastViewed', label: 'Least Viewed' },
              { value: 'mostFavorited', label: 'Most Favorited' },
              { value: 'leastFavorited', label: 'Least Favorited' },
            ]}
          />
        </Box>
      </Box>


      <Grid container spacing={3}>
        {browses.map((browse, index) => {
          const latestSongLarge = index === 0;
          const latestSong = index === 1 || index === 2;

          return (
            <Grid
              key={browse.id}
              size={{
                xs: 12,
                sm: latestSongLarge ? 12 : 6,
                md: latestSongLarge ? 6 : 3,
              }}
            >
              <BrowseItem song={browse} latestSong={latestSong} latestSongLarge={latestSongLarge} />
            </Grid>
          );
        })}
      </Grid>

      <Pagination count={10} color="primary" sx={{ mt: 8, mx: 'auto' }} />
    </DashboardContent>
  );
}
