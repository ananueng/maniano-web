import type { CardProps } from '@mui/material/Card';
import type { IconifyName } from 'src/components/iconify';

import { varAlpha } from 'minimal-shared/utils';

import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Card from '@mui/material/Card';
import Typography from '@mui/material/Typography';

import { fShortenNumber } from 'src/utils/format-number';

import { Iconify } from 'src/components/iconify';

// ----------------------------------------------------------------------

export type IBrowseItem = {
  id: string;
  title: string;
  artist: string;
  postedAt: string;
  avatarUrl: string;
  coverUrl: string;
  isPublic: string;
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
  // const renderAvatar = (
  //   <Avatar
  //     alt={song.author.name}
  //     src={song.author.avatarUrl}
  //     sx={{
  //       left: 24,
  //       zIndex: 9,
  //       bottom: -24,
  //       position: 'absolute',
  //       ...((latestSongLarge || latestSong) && {
  //         top: 24,
  //       }),
  //     }}
  //   />
  // );

  const renderTitle = (
    <Link
      color="inherit"
      variant="subtitle2"
      underline="hover"
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

  const renderInfo = (
    <Box
      sx={{
        mt: 3,
        gap: 1.5,
        display: 'flex',
        flexWrap: 'wrap',
        color: 'text.disabled',
        justifyContent: 'flex-end',
      }}
    >
      {[
        { number: song.totalViews, icon: 'solar:eye-bold' },
        { number: song.totalFavorites, icon: 'solar:star-bold' },
        // { number: song.totalShares, icon: 'solar:share-bold' },
      ].map((info, _index) => (
        <Box
          key={_index}
          sx={{
            display: 'flex',
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
      variant="caption"
      component="div"
      sx={{
        mt: 1,
        color: 'text.disabled',
        ...((latestSongLarge || latestSong) && {
          opacity: 0.48,
          color: 'common.white',
        }),
      }}
    >
      {song.artist}
    </Typography>
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
        {/* {renderAvatar} */}
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
  );
}
