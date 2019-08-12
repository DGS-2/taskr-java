export default theme => ({
    root: {
      padding: '2px 4px',
      display: 'flex',
      alignItems: 'center',
      width: 600,
    },
    checklist: {
      width: '100%',
      // maxWidth: 360,
      backgroundColor: theme.palette.background.paper,
    },
    field: {
      margin: theme.spacing.unit * 3
    },
    flex: {
      display: 'flex',
      alignItems: 'center'
    },
    wrapped: {
      display: 'flex',
      alignItems: 'center',
      flexWrap: 'wrap'
    },
    flexGrow: {
      flexGrow: 1
    },
    avatar: {
      margin: 10,
    },
    chip: {
      margin: '0 .1rem',
    },
    formHolder: {
      width: '50%',
      margin: '0 auto !important'
    },
    center: {
      width: '50%',
      margin: '0 auto !important'
    },
    stepButtons: {
      width: '50%',
      margin: '0.5rem auto !important',
    },
    list: {
      listStyleType: 'none',
      margin: '0 auto !important'
    },
    input: {
      marginLeft: 8,
      flex: 1,
    },
    suggestionsContainerOpen: {
      position: 'absolute',
      zIndex: 1,
      marginTop: theme.spacing(1),
      left: 0,
      right: 0,
    },
    suggestion: {
      display: 'block',
    },
    suggestionsList: {
      margin: 0,
      padding: 0,
      listStyleType: 'none',
    },
    iconButton: {
      padding: 10,
    },
    iconMove: {
      padding: 10,
      cursor: 'move'
    },
    divider: {
      width: 1,
      height: 28,
      margin: 4,
    },
    button: {
      margin: theme.spacing(1),
    },
    backButton: {
      marginRight: theme.spacing(1),
    },
    completed: {
      display: 'inline-block',
    },
    instructions: {
      marginTop: theme.spacing(1),
      marginBottom: theme.spacing(1),
    },
    textField: {
      width: '420px',
      maxWidth: '100%',
      marginRight: theme.spacing.unit * 3
    },
    portletFooter: {
      paddingLeft: theme.spacing.unit * 3,
      paddingRight: theme.spacing.unit * 3,
      paddingTop: theme.spacing.unit * 2,
      paddingBottom: theme.spacing.unit * 2
    },
    ReactTags__tags: {
      position: "relative"
    },
    ReactTags__tagInput: {
      width: '200px',
      borderRadius: '2px',
      display: 'inline-block'
    },
    ReactTags__tagInputField: {
      height: '31px',
      margin: '0',
      fontSize: '12px',
      width: '100%',
      border: '1px solid #eeeeee'
    },
    ReactTags__selected: {
      border: '1px solid #ddd',
      background: '#ffffff',
      fontSize: '12px',
      display: 'inline-block',
      padding: '8px',
      margin: '0 5px',
      borderRadius: '2px',
      color: '#000000',
      marginLeft: '5px',
      cursor: 'pointer'
    },
    ReactTags__suggestions: {
      position: 'absolute',
      zIndex: 1000,
      '& ul': {
        listStyleType: 'none',
        boxShadow: '.05rem .01rem .5rem rgba(0,0,0,.2)',
        width: '200px',
        cursor: 'pointer',
        background: 'white',
        '& li': {
          borderBottom: '1px solid #ddd',
          padding: '1rem',
          margin: 0,
          fontSize: '15px',
          '& mark': {
            textDecoration: 'underline',
            background: '#b7cfe0',
            fontWeight: '600'
          }
        }
      }      
      
    },
    workflowItem: {
      padding: '1rem',
      borderRadius: '5px',
      textAlign: 'center',
      cursor: 'move',
      border: '1px solid black',
      margin: '0.5rem 0'
    },
    container: {
      position: 'relative',
    },
    heading: {
      fontSize: theme.typography.pxToRem(15),
    },
    secondaryHeading: {
      fontSize: theme.typography.pxToRem(15),
      color: theme.palette.text.secondary,
    },
    icon: {
      verticalAlign: 'bottom',
      height: 20,
      width: 20,
    },
    details: {
      alignItems: 'center',
    },
    column: {
      flexBasis: '33.33%',
    },
    helper: {
      borderLeft: `2px solid ${theme.palette.divider}`,
      padding: theme.spacing(1, 2),
    },
    card: {
      maxWidth: 600,
    },
    expand: {
      transform: 'rotate(0deg)',
      marginLeft: 'auto',
      transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
      }),
    },
    delete: {
      transform: 'rotate(0deg)',
      color: '#e60000',
      marginRight: 'auto',
      transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
      }),
    },
    expandOpen: {
      transform: 'rotate(180deg)',
    },
    // avatar: {
    //   backgroundColor: red[500],
    // },
    formControl: {
      margin: theme.spacing(1),
      minWidth: 200,
    },
    selectEmpty: {
      marginTop: theme.spacing(2),
    },
    leftIcon: {
      marginRight: theme.spacing(1),
    },
  });