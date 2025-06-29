import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Radio from '@mui/material/Radio';
import Badge from '@mui/material/Badge';
import Button from '@mui/material/Button';
import Drawer from '@mui/material/Drawer';
import { TextField } from '@mui/material';
import Divider from '@mui/material/Divider';
import Checkbox from '@mui/material/Checkbox';
import FormGroup from '@mui/material/FormGroup';
import RadioGroup from '@mui/material/RadioGroup';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import FormControlLabel from '@mui/material/FormControlLabel';

import { Iconify } from 'src/components/iconify';
import { Scrollbar } from 'src/components/scrollbar';

// ----------------------------------------------------------------------

export type FiltersProps = {
  artist: string[];
  isPublic: string;
  minViews: number;
  minFavorites: number;
  genre: string;
};

type BrowseFiltersProps = {
  canReset: boolean;
  openFilter: boolean;
  filters: FiltersProps;
  onOpenFilter: () => void;
  onCloseFilter: () => void;
  onResetFilter: () => void;
  onSetFilters: (updateState: Partial<FiltersProps>) => void;
  options: {
    artists: { value: string; label: string }[];
    categories: { value: string; label: string }[];
    isPublic: { value: string; label: string }[];
  };
};

export function BrowseFilters({
  filters,
  options,
  canReset,
  openFilter,
  onSetFilters,
  onOpenFilter,
  onCloseFilter,
  onResetFilter,
}: BrowseFiltersProps) {
  const renderArtist = (
    <Stack spacing={1}>
      <Typography variant="subtitle2">Artist</Typography>
      <FormGroup>
        {options.artists.map((option) => (
          <FormControlLabel
            key={option.value}
            control={
              <Checkbox
                checked={filters.artist.includes(option.value)}
                onChange={() => {
                  const checked = filters.artist.includes(option.value)
                    ? filters.artist.filter((value) => value !== option.value)
                    : [...filters.artist, option.value];

                  onSetFilters({ artist: checked });
                }}
              />
            }
            label={option.label}
          />
        ))}
      </FormGroup>
    </Stack>
  );

  const renderGenre = (
    <Stack spacing={1}>
      <Typography variant="subtitle2">Genre</Typography>
      <RadioGroup>
        {options.categories.map((option) => (
          <FormControlLabel
            key={option.value}
            value={option.value}
            control={
              <Radio
                checked={filters.genre === option.value}
                onChange={() => onSetFilters({ genre: option.value })}
              />
            }
            label={option.label}
          />
        ))}
      </RadioGroup>
    </Stack>
  );

  const renderIsPublic = (
    <Stack spacing={1}>
      <Typography variant="subtitle2">Visibility</Typography>
      <RadioGroup>
        {options.isPublic.map((option) => (
          <FormControlLabel
            key={option.value}
            value={option.value}
            control={
              <Radio
                checked={filters.isPublic === option.value}
                onChange={() => onSetFilters({ isPublic: option.value })}
              />
            }
            label={option.label}
          />
        ))}
      </RadioGroup>
    </Stack>
  );

  const renderMinViews = (
    <Stack spacing={1}>
      <Typography variant="subtitle2">Minimum Views</Typography>
      <Box sx={{ display: 'flex', alignItems: 'center' }}>
        <TextField
          type="number"
          size="small"
          slotProps={{ htmlInput: { 'min': 0 } }} 
          defaultValue={0}
          value={filters.minViews}
          onChange={(e) => onSetFilters({ minViews: Number(e.target.value) })}
          sx={{ width: 100, marginRight: 1 }}
        />
        <Typography variant="caption">views</Typography>
      </Box>
    </Stack>
  );

  const renderMinFavorites = (
    <Stack spacing={1}>
      <Typography variant="subtitle2">Minimum Favorites</Typography>
      <Box sx={{ display: 'flex', alignItems: 'center' }}>
        <TextField
          type="number"
          size="small"
          slotProps={{ htmlInput: { 'min': 0 } }} 
          defaultValue={0}
          value={filters.minFavorites}
          onChange={(e) => onSetFilters({ minFavorites: Number(e.target.value) })}
          sx={{ width: 100, marginRight: 1 }}
          variant="outlined"
        />
        <Typography variant="caption">favorites</Typography>
      </Box>
    </Stack>
  );

  return (
    <>
      <Button
        disableRipple
        color="inherit"
        endIcon={
          <Badge color="error" variant="dot" invisible={!canReset}>
            <Iconify icon="ic:round-filter-list" />
          </Badge>
        }
        onClick={onOpenFilter}
      >
        Filters
      </Button>

      <Drawer
        anchor="right"
        open={openFilter}
        onClose={onCloseFilter}
        slotProps={{
          paper: {
            sx: { width: 280, overflow: 'hidden' },
          },
        }}
      >
        <Box
          sx={{
            py: 2,
            pl: 2.5,
            pr: 1.5,
            display: 'flex',
            alignItems: 'center',
          }}
        >
          <Typography variant="h6" sx={{ flexGrow: 1 }}>
            Filters
          </Typography>

          <IconButton onClick={onResetFilter}>
            <Badge color="error" variant="dot" invisible={!canReset}>
              <Iconify icon="solar:restart-bold" />
            </Badge>
          </IconButton>

          <IconButton onClick={onCloseFilter}>
            <Iconify icon="mingcute:close-line" />
          </IconButton>
        </Box>

        <Divider />

        <Scrollbar>
          <Stack spacing={3} sx={{ p: 3 }}>
            {renderArtist}
            {renderGenre}
            {renderIsPublic}
            {renderMinViews}
            {renderMinFavorites}
          </Stack>
        </Scrollbar>
      </Drawer>
    </>
  );
}
