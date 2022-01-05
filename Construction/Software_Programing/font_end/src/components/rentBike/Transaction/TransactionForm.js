import React, { useState } from 'react'
import { Button, Form } from 'react-bootstrap'
import "bootstrap/dist/css/bootstrap.min.css"
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import {DEPOSIT_TYPE} from '../../../variables/ConstantCommon'
function TransactionForm(props) {
    const history = useHistory();
    const [card, setCard] = useState({
        owner: '',
        cardCode: '',
        dateExpired: '',
        cvvCode: ''
    })
    const [rentDay, setRentDay] = useState(true);
    const [error, setError] = useState();
    const [success, setSuccess] = useState();
    const {bike} = props;
    const { owner, cardCode, dateExpired, cvvCode } = card;

    const handleOnChange = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'owner':
                setCard(prev => ({ ...prev, owner: value }))
                break;
            case 'cardCode':
                setCard(prev => ({ ...prev, cardCode: value }))
                break;
            case 'dateExpired':
                setCard(prev => ({ ...prev, dateExpired: value }))
                break;
            case 'cvvCode':
                setCard(prev => ({ ...prev, cvvCode: value }))
                break;
            default:
                break;
        }
    }

    const handleOnSubmit = (event) => {
        event.preventDefault();
        const paymentForm = {card, amount: DEPOSIT_TYPE[bike.type], rentDay};
        const paymentFormJSON = JSON.stringify(paymentForm);
        
        (async (paymentFormJSON, id) => {
            try {
            const resp = await axios.post("http://localhost:8080/api/rentbike/deposit/" + id, paymentFormJSON, {
                headers: {"Content-Type": "application/json"}
            });
            if (resp.status === 200) {
                const {amount, card} = resp.data;
                setSuccess("Giao dịch thành công");
                setError();
                history.push({
                    pathname: "/rent-bike/transaction-result",
                    bike: bike,
                    amount: amount,
                    card: card 
                })

            } else {
                console.log("error transaction");
            } 
            } catch (err) {
                setError("Giao dịch không thành công, mời bạn nhập lại thông tin thẻ")
            }
            
        }) (paymentFormJSON, bike.id);
    }
    return (
        <div>
            {error && <div style={{color: 'red', fontWeight: 'bold'}}>{error}</div>}
            {success && <div style={{color: 'green', fontWeight: 'bold'}}>{success}</div>}
            <Form onSubmit={handleOnSubmit}>
                <Form.Group className="mb-3" controlId="ower">
                    <Form.Label>Owner:</Form.Label>
                    <Form.Control type="text" placeholder="Enter the name of ower" name='owner' onChange={handleOnChange} value={owner} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="cardCode">
                    <Form.Label>Card Code:</Form.Label>
                    <Form.Control type="text" placeholder="Enter card code" name="cardCode" onChange={handleOnChange} value={cardCode} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="dateExpired">
                    <Form.Label>Date Expired:</Form.Label>
                    <Form.Control type="text" placeholder="Enter the expiration date" name="dateExpired" onChange={handleOnChange} value={dateExpired} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="cvvCode">
                    <Form.Label>Cvv Code:</Form.Label>
                    <Form.Control type="text" placeholder="Enter the cvv code" name="cvvCode" onChange={handleOnChange} value={cvvCode} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="rentDay">
                    <Form.Label>Thuê trong ngày:</Form.Label>
                    <input 
                        style={{width: '15px', height: '15px', marginLeft: '20px', paddingTop: '10px'}} 
                        type="checkbox" 
                        name="isDayRent"
                        checked={rentDay}
                        onClick={() => setRentDay(prev => !prev)}/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    )
}

export default TransactionForm
