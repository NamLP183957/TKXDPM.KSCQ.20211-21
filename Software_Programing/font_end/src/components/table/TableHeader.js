import React from 'react'

const headerStyle = {
    fontWeight: 'bold',
    fontSize: '20px',
    // borderRight: '1px solid gray',
    padding: '10px',
    borderRadius: "20px"
}

function TableHeader(props) {
    return (
        <th style={headerStyle}>
            {props.children}
        </th>
    )
}

export default TableHeader
