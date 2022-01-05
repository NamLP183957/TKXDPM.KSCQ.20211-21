import React from 'react'

const dataStyle = {
    // borderRight: '1px solid gray',
    fontSize: '18px',
    padding: '10px',
    "&:hover": {
        background: "#efefef"
      }
}

function TableData(props) {
    return (
        <td style={dataStyle}>
            {props.children}
        </td>
    )
}

export default TableData
