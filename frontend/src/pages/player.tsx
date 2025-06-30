import { CONFIG } from 'src/config-global';

import { OverviewAnalyticsView as PlayerView } from 'src/sections/overview/view';

// ----------------------------------------------------------------------

export default function Page() {
  return (
    <>
      <title>{`Editor - ${CONFIG.appName}`}</title>

      <PlayerView />
    </>
  );
}
