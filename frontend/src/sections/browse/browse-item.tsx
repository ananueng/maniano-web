import type { CardProps } from '@mui/material/Card';
import type { IconifyName } from 'src/components/iconify';

import { varAlpha } from 'minimal-shared/utils';

import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Card from '@mui/material/Card';
import Typography from '@mui/material/Typography';

import { RouterLink } from 'src/routes/components';

import { fShortenNumber } from 'src/utils/format-number';

import { Label } from 'src/components/label';
import { Iconify } from 'src/components/iconify';

// ----------------------------------------------------------------------

export type IBrowseItem = {
  id: string;
  title: string;
  artist: string;
  status: string;
  genre: string;
  postedAt: string;
  avatarUrl: string;
  coverUrl: string;
  isPublic: boolean;
  totalViews: number;
  totalComments: number;
  totalShares: number;
  totalFavorites: number;
};

export function BrowseItem({
  sx,
  song,
  latestSong,
  latestSongLarge,
  ...other
}: CardProps & {
  song: IBrowseItem;
  latestSong: boolean;
  latestSongLarge: boolean;
}) {
  const statusColorMap: Record<string, 'success' | 'error' | 'warning' | 'info' | 'default'> = {
    verified: 'success',
    archived: 'default',
    pending: 'info',
  };

  const renderStatus = (
    <Label
      variant="inverted"
      color={statusColorMap[song.status] || 'default'}
      sx={{
        position: 'absolute',
        top: 16,
        left: 16,
        zIndex: 9,
        textTransform: 'capitalize',
      }}
    >
      {song.status}
    </Label>
  );

  const renderTitle = (
    <Link
      color="inherit"
      variant="subtitle1"
      underline="none"
      sx={{
        overflow: 'hidden',
        WebkitLineClamp: 2,
        display: '-webkit-box',
        WebkitBoxOrient: 'vertical',
        ...(latestSongLarge && { typography: 'h5' }),
        ...((latestSongLarge || latestSong) && {
          color: 'common.white',
        }),
      }}
    >
      {song.title}
    </Link>
  );

  const renderCover = (
    <Box
      component="img"
      alt={song.title}
      src={song.coverUrl}
      sx={{
        top: 0,
        width: 1,
        height: 1,
        objectFit: 'cover',
        position: 'absolute',
      }}
    />
  );

  const renderArtist = (
    <Typography
      variant="body2"
      component="div"
      sx={{
        color: 'inherit',
        ...((latestSongLarge || latestSong) && {
          color: 'common.white',
        }),
      }}
    >
      {song.artist}
    </Typography>
  );


  const renderInfo = (
    <Box
      sx={{
        mt: 1,
        display: 'flex',
        alignItems: 'center',
        color: 'text.disabled',
        justifyContent: 'space-between',
      }}
    >
      <Typography variant="caption">
        {song.genre.charAt(0).toUpperCase() + song.genre.slice(1)}
      </Typography>
      <Box
        sx={{
          display: 'flex',
          gap: 1.5,
          alignItems: 'center',
        }}
      >
        {[
          { number: song.totalViews, icon: 'solar:eye-bold' },
          { number: song.totalFavorites, icon: 'eva:star-outline' },
        ].map((info, _index) => (
          <Box
            key={_index}
            sx={{
              display: 'flex',
              alignItems: 'center',
              ...((latestSongLarge || latestSong) && {
                opacity: 0.64,
                color: 'common.white',
              }),
            }}
          >
            <Iconify width={16} icon={info.icon as IconifyName} sx={{ mr: 0.5 }} />
            <Typography variant="caption">{fShortenNumber(info.number)}</Typography>
          </Box>
        ))}
      </Box>
    </Box>
  );

  // const renderShape = (
  //   <SvgColor
  //     src="/assets/icons/shape-avatar.svg"
  //     sx={{
  //       left: 0,
  //       width: 88,
  //       zIndex: 9,
  //       height: 36,
  //       // bottom: -16,
  //       position: 'absolute',
  //       color: 'background.paper',
  //       ...((latestSongLarge || latestSong) && { display: 'none' }),
  //     }}
  //   />
  // );

  return (
    <Link
      component={RouterLink}
      href={`/song/${song.id}`}
      underline="none"
      sx={{
        display: 'block',
        cursor: 'pointer',
        '&:hover .MuiCard-root': {
          boxShadow: 16,
          transform: 'scale(1.02)',
        },
      }}
    >
      <Card sx={sx} {...other}>
        <Box
          sx={(theme) => ({
            position: 'relative',
            pt: 'calc(100% * 3 / 4)',
            ...((latestSongLarge || latestSong) && {
              pt: 'calc(100% * 4 / 3)',
              '&:after': {
                top: 0,
                content: "''",
                width: '100%',
                height: '100%',
                position: 'absolute',
                bgcolor: varAlpha(theme.palette.grey['900Channel'], 0.72),
              },
            }),
            ...(latestSongLarge && {
              pt: {
                xs: 'calc(100% * 4 / 3)',
                sm: 'calc(100% * 3 / 4.66)',
              },
            }),
          })}
        >
          {/* {renderShape} */}
          {renderStatus}
          {renderCover}
        </Box>

        <Box
          sx={(theme) => ({
            p: theme.spacing(2, 2, 2, 2),
            ...((latestSongLarge || latestSong) && {
              width: 1,
              bottom: 0,
              position: 'absolute',
            }),
          })}
        >
          {renderTitle}
          {renderArtist}
          {renderInfo}
        </Box>
      </Card>
    </Link>
  );
}
