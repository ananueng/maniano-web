import type { IconifyName } from 'src/components/iconify';

import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Card from '@mui/material/Card';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';

import { fShortenNumber } from 'src/utils/format-number';

import { Iconify } from 'src/components/iconify';

// ----------------------------------------------------------------------

export type SongItemProps = {
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

export function OldSongItem({ song }: { song: SongItemProps }) {
  // const renderStatus = (
  //   <Label
  //     variant="inverted"
  //     color={(song.status === 'sale' && 'error') || 'info'}
  //     sx={{
  //       zIndex: 9,
  //       top: 16,
  //       right: 16,
  //       position: 'absolute',
  //       textTransform: 'uppercase',
  //     }}
  //   >
  //     {song.status}
  //   </Label>
  // );

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
        { number: song.totalComments, icon: 'solar:chat-round-dots-bold' },
        { number: song.totalViews, icon: 'solar:eye-bold' },
        { number: song.totalShares, icon: 'solar:share-bold' },
      ].map((info, _index) => (
        <Box
          key={_index}
          sx={{
            display: 'flex',
            // ...((latestPostLarge || latestPost) && {
            //   opacity: 0.64,
            //   color: 'common.white',
            // }),
          }}
        >
          <Iconify width={16} icon={info.icon as IconifyName} sx={{ mr: 0.5 }} />
          <Typography variant="caption">{fShortenNumber(info.number)}</Typography>
        </Box>
      ))}
    </Box>
  );

  const renderImg = (
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

  // const renderPrice = (
  //   <Typography variant="subtitle1">
  //     <Typography
  //       component="span"
  //       variant="body1"
  //       sx={{
  //         color: 'text.disabled',
  //         textDecoration: 'line-through',
  //       }}
  //     >
  //       {song.priceSale && fCurrency(song.priceSale)}
  //     </Typography>
  //     &nbsp;
  //     {fCurrency(song.price)}
  //   </Typography>
  // );

  return (
    <Card>
      <Box sx={{ pt: '100%', position: 'relative' }}>
        {/* {song.status && renderStatus} */}
        {renderImg}
      </Box>

      <Stack spacing={2} sx={{ p: 3 }}>
        <Link color="inherit" underline="hover" variant="subtitle2" noWrap>
          {song.title}
        </Link>

        <Box
          sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'space-between',
          }}
        >
          {/* <ColorPreview colors={song.colors} /> */}
          {/* {renderPrice} */}
          {renderInfo}
        </Box>
      </Stack>
    </Card>
  );
}
