import type { Theme, SxProps } from '@mui/material/styles';

import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import Autocomplete, { autocompleteClasses } from '@mui/material/Autocomplete';

import { Iconify } from 'src/components/iconify';

// import type { IsongItem } from './song-item';

// ----------------------------------------------------------------------

type BrowseSearchProps = {
  songs: ISongItem[];
  sx?: SxProps<Theme>;
};

type ISongItem = {
  id: string;
  title: string;
  artist: string;
  genre: string;
  status: string;
  isPublic: boolean;
};

export function BrowseSearch({ songs, sx }: BrowseSearchProps) {
  return (
    <Autocomplete
      sx={{ width: 280 }}
      autoHighlight
      popupIcon={null}
      slotProps={{
        paper: {
          sx: {
            width: 320,
            [`& .${autocompleteClasses.option}`]: {
              typography: 'body2',
            },
            ...sx,
          },
        },
      }}
      options={songs}
      getOptionLabel={(song) => song.title}
      isOptionEqualToValue={(option, value) => option.id === value.id}
      renderInput={(params) => (
        <TextField
          {...params}
          placeholder="Search song..."
          slotProps={{
            input: {
              ...params.InputProps,
              startAdornment: (
                <InputAdornment position="start">
                  <Iconify
                    icon="eva:search-fill"
                    sx={{ ml: 1, width: 20, height: 20, color: 'text.disabled' }}
                  />
                </InputAdornment>
              ),
            },
          }}
        />
      )}
    />
  );
}
