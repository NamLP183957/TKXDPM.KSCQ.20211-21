import React from 'react'

const PopupBodyStyle = {
    fontSize: '25px',
    padding: '20px'
}
function PopupBody(props) {

    return (
        <div style={PopupBodyStyle}>
            {props.children}
        </div>
    )
}

export default PopupBody
