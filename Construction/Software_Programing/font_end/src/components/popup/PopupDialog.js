import React, { useState } from 'react'
import './Popup.css'
function PopupDialog(props) {
    const [toggle, setToggle] = useState(true);
    const handleOnClick = () => {
        setToggle(false);
    }
    return (
        <>
            {toggle && 
            <div className="popup-box">
                <div className="box">
                    <span className="close-icon" onClick={() => props.handleOnClose()}>x</span>
                    {props.children}
                </div>
            </div>}
        </>
    )
}

export default PopupDialog
