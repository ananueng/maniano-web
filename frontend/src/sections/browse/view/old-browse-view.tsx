// import { useState, useCallback } from 'react';

// import Box from '@mui/material/Box';
// import Grid from '@mui/material/Grid';
// import Pagination from '@mui/material/Pagination';
// import Typography from '@mui/material/Typography';

// import { _songs } from 'src/_mock';
// import { DashboardContent } from 'src/layouts/dashboard';

// import { BrowseItem } from '../browse-item';
// import { BrowseSort } from '../browse-sort';
// import { BrowseSearch } from '../browse-search';
// import { BrowseFilters } from '../browse-filters';

// import type { FiltersProps } from '../browse-filters';

// // ----------------------------------------------------------------------

// const GENDER_OPTIONS = [
//   { value: 'men', label: 'Men' },
//   { value: 'women', label: 'Women' },
//   { value: 'kids', label: 'Kids' },
// ];

// const GENRE_OPTIONS = [
//   { value: 'all', label: 'All' },
//   { value: 'shose', label: 'Shose' },
//   { value: 'apparel', label: 'Apparel' },
//   { value: 'accessories', label: 'Accessories' },
// ];

// const RATING_OPTIONS = ['up4Star', 'up3Star', 'up2Star', 'up1Star'];

// const PRICE_OPTIONS = [
//   { value: 'below', label: 'Below $25' },
//   { value: 'between', label: 'Between $25 - $75' },
//   { value: 'above', label: 'Above $75' },
// ];

// const COLOR_OPTIONS = [
//   '#00AB55',
//   '#000000',
//   '#FFFFFF',
//   '#FFC0CB',
//   '#FF4842',
//   '#1890FF',
//   '#94D82D',
//   '#FFC107',
// ];

// const defaultFilters = {
//   price: '',
//   gender: [GENDER_OPTIONS[0].value],
//   colors: [COLOR_OPTIONS[4]],
//   rating: RATING_OPTIONS[0],
//   genre: GENRE_OPTIONS[0].value,
// };

// export function OldBrowseView() {
//   const [sortBy, setSortBy] = useState('featured');

//   const [openFilter, setOpenFilter] = useState(false);

//   const [filters, setFilters] = useState<FiltersProps>(defaultFilters);

//   const handleOpenFilter = useCallback(() => {
//     setOpenFilter(true);
//   }, []);

//   const handleCloseFilter = useCallback(() => {
//     setOpenFilter(false);
//   }, []);

//   const handleSort = useCallback((newSort: string) => {
//     setSortBy(newSort);
//   }, []);

//   const handleSetFilters = useCallback((updateState: Partial<FiltersProps>) => {
//     setFilters((prevValue) => ({ ...prevValue, ...updateState }));
//   }, []);

//   const canReset = Object.keys(filters).some(
//     (key) => filters[key as keyof FiltersProps] !== defaultFilters[key as keyof FiltersProps]
//   );

//   return (
//     <DashboardContent>
//       {/* <CartIcon totalItems={8} /> */}

//       <Typography variant="h4" sx={{ mb: 5 }}>
//         Browse
//       </Typography>
//       <Box
//         sx={{
//           mb: 5,
//           display: 'flex',
//           alignItems: 'center',
//           justifyContent: 'space-between',
//         }}
//       >
//         <BrowseSearch songs={_songs} />
//         <Box
//           sx={{
//             display: 'flex',
//             alignItems: 'center',
//             gap: 2,
//           }}
//         >
//           <BrowseFilters
//             canReset={canReset}
//             filters={filters}
//             onSetFilters={handleSetFilters}
//             openFilter={openFilter}
//             onOpenFilter={handleOpenFilter}
//             onCloseFilter={handleCloseFilter}
//             onResetFilter={() => setFilters(defaultFilters)}
//             options={{
//               genders: GENDER_OPTIONS,
//               categories: GENRE_OPTIONS,
//               ratings: RATING_OPTIONS,
//               price: PRICE_OPTIONS,
//               colors: COLOR_OPTIONS,
//             }}
//           />

//           <BrowseSort
//             sortBy={sortBy}
//             onSort={handleSort}
//             options={[
//               { value: 'featured', label: 'Featured' },
//               { value: 'newest', label: 'Newest' },
//               { value: 'priceDesc', label: 'Price: High-Low' },
//               { value: 'priceAsc', label: 'Price: Low-High' },
//             ]}
//           />
//         </Box>
//       </Box>

//       <Grid container spacing={3}>
//         {_songs.map((song) => (
//           <Grid key={song.id} size={{ xs: 12, sm: 6, md: 3 }}>
//             <BrowseItem song={song} latestSong={false} latestSongLarge={false}/>
//           </Grid>
//         ))}
//       </Grid>

//       <Pagination count={10} color="primary" sx={{ mt: 8, mx: 'auto' }} />
//     </DashboardContent>
//   );
// }
