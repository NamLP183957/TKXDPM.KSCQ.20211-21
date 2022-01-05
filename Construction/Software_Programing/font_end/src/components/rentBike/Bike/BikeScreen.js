import React from 'react'
import { useHistory } from 'react-router-dom';
import BikeInformation from './BikeInformation'
import {AiOutlineStepBackward} from "react-icons/ai"
import {BiSkipNext} from "react-icons/bi"
import { Button } from 'react-bootstrap';
function BikeScreen(props) {
    const history = useHistory();
    const {state} = props.location;
    console.log(state);

    const handleOnClick = () => {
        history.push({
            pathname: '/rent-bike/deposit',
            state: state
        })
    }

    const handleOnReturnClick = () => {
        history.push({
            pathname: '/rent-bike/'
        })
    }
    return (
        <div>
            <BikeInformation bike={state}/>
            <br />
            <Button variant='secondary' onClick={() => handleOnReturnClick()}>
                <AiOutlineStepBackward /> Quay lại
            </Button>
            <span />
            <Button variant='secondary' onClick={() => handleOnClick()}>
                Thuê xe <BiSkipNext style={{color: 'white'}}/>
            </Button>

        </div>
    )
}

export default BikeScreen
