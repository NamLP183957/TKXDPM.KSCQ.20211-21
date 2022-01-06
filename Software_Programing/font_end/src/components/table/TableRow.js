import React from 'react'

const rowStyle = {
    borderBottom: "1px solid gray"
}
function TableRow(props) {
    return (
        <tr style={rowStyle}>
            {props.children}
        </tr>
    )
}

export default TableRow
