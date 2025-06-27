import { _songs } from 'src/_mock';
import { CONFIG } from 'src/config-global';

import { BrowseView } from 'src/sections/browse/view/browse-view';

// ----------------------------------------------------------------------

export default function Page() {
  return (
    <>
      <title>{`Browse - ${CONFIG.appName}`}</title>

      <BrowseView browses={_songs}/>
    </>
  );
}
