export default theme => ({
    root: {
      borderBottom: `1px solid ${theme.palette.border}`,
      backgroundColor: theme.palette.common.white,
      display: 'flex',
      alignItems: 'center',
      height: '64px',
      zIndex: theme.zIndex.appBar
    },
    toolbar: {
      minHeight: 'auto',
      width: '100%'
    },
    title: {
      marginLeft: theme.spacing.unit
    },
    menuButton: {
      marginLeft: '-4px'
    },
    notificationsButton: {
      marginLeft: theme.spacing.unit
    },
    signOutButton: {
      marginLeft: theme.spacing.unit
    },
    addWorkflowButton: {
      marginLeft: 'auto'
    }
  });