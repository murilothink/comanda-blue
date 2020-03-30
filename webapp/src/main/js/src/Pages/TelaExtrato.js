import React, { useContext } from 'react';
import api from '../Services/api';
import '../style.css';
import CssBaseline from '@material-ui/core/CssBaseline';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import ClickAwayListener from '@material-ui/core/ClickAwayListener';
import Grow from '@material-ui/core/Grow';
import Paper from '@material-ui/core/Paper';
import Popper from '@material-ui/core/Popper';
import MenuItem from '@material-ui/core/MenuItem';
import MenuList from '@material-ui/core/MenuList';
import PropTypes from 'prop-types';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import { FixedSizeList } from 'react-window';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { Grid } from '@material-ui/core';
import FormControlLabel from '@material-ui/core/FormControlLabel';
// Para utilizar inputs. Cada FormControl pode ter 1 input


const useStyles = makeStyles((theme) => ({
    root: {
      width: '100%',
      height: 400,
      maxWidth: 300,
      backgroundColor: theme.palette.background.paper,
    },
  }));
  
  function renderRow(props) {
    const { index, style } = props;
  
    return (
      <ListItem button style={style} key={index}>
        <ListItemText primary={`Item ${index + 1}`} />
      </ListItem>
    );
  }
  
  renderRow.propTypes = {
    index: PropTypes.number.isRequired,
    style: PropTypes.object.isRequired,
  };

export default function TelaExtrato(props){

    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const anchorRef = React.useRef(null);
    const handleToggle = () => {
        setOpen((prevOpen) => !prevOpen);
    };
    const handleClose = (event) => {
        if (anchorRef.current && anchorRef.current.contains(event.target)) {
        return;
        }

        setOpen(false);
    };
    function handleListKeyDown(event) {
        if (event.key === 'Tab') {
        event.preventDefault();
        setOpen(false);
        }
    }

    return(
          <div
          container
          style={{height:"100%"}}
          spacing={0}
          justify="center"
          direction="column"
          >
            <Grid
                container style={{ height: "30%" }}
                direction="row"
                spacing={0}
                align="center"
                justify="center"
            >

            <Grid
            container                    
            spacing={0}
            align="left"
            justify="center"
            direction="column" item xs={'auto'} sm={6}
            >
                <Button 
                style={{ top: '72px', background: '#2d9bf0', color: 'white' }}
                align="left" 
                variant="contained" 
                color="primary"
                >
                Voltar ao Menu
                </Button>
            </Grid>

            <Grid
            container                    
            spacing={0}
            align="center"
            justify="center"
            direction="column" item xs={12} sm={6} className="extrato"
            >
                <Typography 
                component="h1" 
                variant="h5"  
                align="center"
                >
                    Extrato:
                </Typography>
            </Grid>  

            <Grid
            container                    
            spacing={0}
            align="right"
            direction="column" item xs={12} sm={6}
            >
                    <Button style={{background: '#2d9bf0', color: 'white' }}
                    ref={anchorRef}
                    aria-controls={open ? 'menu-list-grow' : undefined}
                    aria-haspopup="true"
                    onClick={handleToggle}
                    align="right"
                    >
                    Filtrar
                    </Button>
                    <Popper open={open} anchorEl={anchorRef.current} role={undefined} transition disablePortal>
                    {({ TransitionProps, placement }) => (
                        <Grow
                        {...TransitionProps}
                        style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
                        >
                        <Paper>
                            <ClickAwayListener onClickAway={handleClose}>
                            <MenuList autoFocusItem={open} id="menu-list-grow" onKeyDown={handleListKeyDown}>
                                <MenuItem onClick={handleClose}>Mesa</MenuItem>
                                <MenuItem onClick={handleClose}>Nome</MenuItem>
                            </MenuList>
                            </ClickAwayListener>
                        </Paper>
                        </Grow>
                    )}
                    </Popper>
                </Grid>
            </Grid>

                <div> 
                    <FixedSizeList height={400} width={"100%"} itemSize={46} itemCount={200}>
                    {renderRow}
                    </FixedSizeList>
                </div>

                <div>
                        <Typography 
                        component="h1" 
                        variant="h5"  
                        align="center"
                        >
                            Total:
                        </Typography>

                        <Button 
                        style={{ top: '72px', background: '#2d9bf0', color: 'white' }} 
                        variant="contained" 
                        color="primary"
                        >
                            Pagar
                        </Button>
                </div>

        </div>     
          
  
    )
}