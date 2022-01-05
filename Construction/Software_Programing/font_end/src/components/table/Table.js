import React from 'react'

const tableStyle={
    width: '100%',
    fontSize: '20px'
}
function Table(props) {
    return (
        <table style={tableStyle}>
            {props.children}
        </table>
    )
}

export default Table
