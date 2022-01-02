import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import axios from 'axios'
import "bootstrap/dist/css/bootstrap.min.css"
import { useHistory } from 'react-router-dom';
import PopupDialog from '../../popup/PopupDialog';
import PopupTitle from '../../popup/PopupTitle';
import PopupBody from '../../popup/PopupBody';
import PopupFooter from '../../popup/PopupFooter';
import logo from '../../../logo.png'
import {postBikeCode} from '../../../services/RentBikeService'
function BikeCodeForm(props) {
    const [bikeCode, setBikeCode] = useState('');
    const [error, setError] = useState();
    const history = useHistory();

    const handleOnChange = (event) => {
        setBikeCode(event.target.value);
    }

    const handleOnSubmit = () => {
        console.log("bikeCode: " + bikeCode);
        (async (bikeCode) => {
            try {
            // const resp = await axios.post("http://localhost:8080/api/rentbike/process-bike-code", bikeCode, {
            //     headers: {"Content-Type": "application/json"}
            // });
            const resp = postBikeCode(bikeCode);
            if (resp.status !== 200) {
                console.log("error");
            } else {
                history.push({
                    pathname: '/rent-bike/bike-info',
                    state: resp.data
                })
            }
            } catch (error) {
                setError("Mã code không đúng hoặc ô trống không có xe");
            }
            
        })(bikeCode);
    }
    return (
        <div>
        <PopupDialog handleOnClose={props.handleOnClose}>
            <PopupTitle>
                    <h2><img src={logo} style={{width: '150px', height: '100px'}} />Nhập mã code</h2>
                {error && <div style={{color: 'red', fontSize: '18px'}}>{error}</div>}
            </PopupTitle>

            <PopupBody>
                <label>Code: <span></span></label>
                <input 
                    style={{width: '80%', fontSize: '20px', paddingLeft: '10px', borderRadius: '10px'}}
                    type="text" 
                    value={bikeCode}
                    placeholder='Nhập mã bike code...'
                    onChange={handleOnChange}
                />
            </PopupBody>
            <PopupFooter>
                <Button onClick={() => handleOnSubmit()}>Submit</Button>
            </PopupFooter>
        </PopupDialog>
        {/* <Modal.Dialog>
            <Modal.Header closeButton>
                <Modal.Title>Enter bike code here</Modal.Title>
            </Modal.Header>

            <Modal.Body>
                <input 
                    type="text" 
                    value={bikeCode}
                    onChange={handleOnChange}
                />
            </Modal.Body>

            <Modal.Footer>
                <Button variant='secondary'>Close</Button>
                <Button variant='primary' onClick={() => handleOnSubmit()}>Submit</Button>
            </Modal.Footer>
        </Modal.Dialog> */}
        </div>
    )
}

export default BikeCodeForm
