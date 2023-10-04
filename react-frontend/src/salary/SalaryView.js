
import React from "react";
import { makeStyles } from "@material-ui/core/styles";
  
// Importing material UI components
import List from "@material-ui/core/List";
import ListSubheader from "@material-ui/core/ListSubheader";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemSecondaryAction from "@material-ui/core/ListItemSecondaryAction";
import ListItemAvatar from "@material-ui/core/ListItemAvatar";
import ListItemText from "@material-ui/core/ListItemText";
import Grid from "@material-ui/core/Grid";
import IconButton from "@material-ui/core/IconButton";
import Avatar from "@material-ui/core/Avatar";
import Collapse from "@material-ui/core/Collapse";
import Checkbox from "@material-ui/core/Checkbox";
  
// Importing material UI icons
import InboxIcon from "@material-ui/icons/MoveToInbox";
import ExpandLess from "@material-ui/icons/ExpandLess";
import ExpandMore from "@material-ui/icons/ExpandMore";
import FolderIcon from "@material-ui/icons/Folder";
import DeleteIcon from "@material-ui/icons/Delete";
  
const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
  nested: {
    paddingLeft: theme.spacing(4),
  },
  demo: {
    backgroundColor: theme.palette.background.paper,
  },
}));
  
export default function SalaryView() {
  const classes = useStyles();
  const [open, setOpen] = React.useState(true);
  const [secondary, setSecondary] = React.useState(false);
  const handleClick = () => {
    setOpen(!open);
  };
  return (
    <Grid item xs={12} md={6}>
      <div className={classes.demo}>
      {/* If checkbox is ticked then secondary text will be shown otherwise not */}
        <Checkbox
          checked={secondary}
          onChange={(event) => setSecondary(event.target.checked)}
        />
        <List
          component="nav"
          aria-labelledby="nested-list-subheader"
          subheader={
            <ListSubheader component="div" id="nested-list-subheader">
              mark the checkbox above to see sublist
            </ListSubheader>
          }
          className={classes.root}
        >
          <ListItem button onClick={handleClick}>
            <ListItemIcon>

            </ListItemIcon>
            <ListItemText primary="Відкрити" />
            {/*code to open and closed list*/}
          </ListItem>
          <Collapse in={open} timeout="auto" unmountOnExit>
            {/*List item are wrapped inside List */}
            <List component="div" disablePadding> 
              <ListItem> {/* Single list item */}
                <ListItemAvatar>
                  <Avatar>
                    <FolderIcon />
                  </Avatar>
                </ListItemAvatar>
                <ListItemText
                  primary="Департаменти"
                  secondary={
                    secondary ? "Structured premium video lectures" : null
                  }
                />
                <ListItemSecondaryAction>
                  {/*Inside the IconButton, we can render various icons*/}
                  <IconButton edge="end" aria-label="delete">
                  </IconButton>
                </ListItemSecondaryAction>
              </ListItem>
              <ListItem>
                <ListItemAvatar>
                  <Avatar>
                    <FolderIcon />
                  </Avatar>
                </ListItemAvatar>
                <ListItemText
                  primary="Інститути"
  
                   // if secondary variable state is true then show
                   // text otherwise null i.e. nothing will be shown
                  secondary={
                    secondary
                      ? "An interview-centric course designed to prepare you for the role of SDE"
                      : null
                  }
                />
                <ListItemSecondaryAction>
                  <IconButton edge="end" aria-label="delete">
                  </IconButton>
                </ListItemSecondaryAction>
              </ListItem>
              <ListItem>
                <ListItemAvatar>
                  <Avatar>
                    <FolderIcon />
                  </Avatar>
                </ListItemAvatar>
                <ListItemText
                  primary="Підрозділи"
                  secondary={
                    secondary
                      ? "Real Time Live Classes accessible from home"
                      : null
                  }
                />
                <ListItemSecondaryAction>
                  <IconButton edge="end" aria-label="delete">
                  </IconButton>
                </ListItemSecondaryAction>
              </ListItem>
            </List>
          </Collapse>
        </List>
      </div>
    </Grid>
  );
}