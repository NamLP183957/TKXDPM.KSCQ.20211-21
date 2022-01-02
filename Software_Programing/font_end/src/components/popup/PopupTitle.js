import React from 'react'

const PopupTitleStyle = {
    fontSize: "30px",
    borderBottom: '1px solid gray',
    fontWeight: 'bold',
    textAlign: 'center',
    paddingBottom: '20px'
}

function PopupTitle(props) {
    return (
        <div style={PopupTitleStyle}>
            {props.children}
        </div>
    )
}

export default PopupTitle
