import React from 'react'

const PopupFooterStyle = {
    borderTop: "1px solid gray",
    textAlign: 'right',
    fontSize: '25px',
    paddingTop: '20px'
}

function PopupFooter(props) {
    return (
        <div style={PopupFooterStyle}>
            {props.children}
        </div>
    )
}

export default PopupFooter
