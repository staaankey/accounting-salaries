import { useState } from "react";
import { useRef } from "react";
import { v4 as uuidv4 } from 'uuid';


export default function Table({ colProps }) {
    const [rows, setRows] = useState([]);
    const rowData = useRef({}).current;

    function handleChange(e) {
        rowData[e.target.id] = e.target.value;
    }

    return (
        <main className="table">
            <ul className="table__row">
                {
                    colProps.map(prop => {
                        return <li className="table__row-head" key={prop.id}>{prop.name}</li>
                    })
                }
            </ul>
            {
                rows.map(row => {
                    return <TableRow colProps={colProps} rowData={rowData} key={row}></TableRow>;
                })
            }
            <ul className="table__row">
                {
                    colProps.map(prop => {
                        return (
                            <li key={prop.id} className="table__row-el">
                                <input className="table__row-el-inp" onChange={handleChange} id={prop.id} type={prop.type} required={prop.req}></input>
                            </li>
                        );
                    })
                }
                <li className="table__row-el">
                    <button type="submit" onClick={ () => setRows([...rows, uuidv4()]) } className="table__crclBtn table__crclBtn_onAdd"></button>
                </li>
            </ul>
        </main>
    );
}

function TableRow({ colProps, rowData }) {
    const [editable, setEditable] = useState(false);
    const editData = useRef({...rowData}).current;

    function handleChange(e) {
        editData[e.target.id] = e.target.value;
    }

    if (editable) {
        return (
            <ul className="table__row">
                {
                    colProps.map(prop => {
                        return (
                            <li className="table__row-el" key={prop.id}>
                                <input className="table__row-el-inp" placeholder={ editData[prop.id] } onChange={handleChange} id={prop.id} type={prop.type} required={prop.req}></input>
                            </li>
                        );
                    })
                }
                <li className="table__row-el">
                    <button className="table__crclBtn table__crclBtn_onEdit" onClick={ () => setEditable(!editable) }></button>
                </li>
            </ul>
        );
    } else {
        return (
            <ul className="table__row">
                {
                    colProps.map(prop => {
                        return (
                            <li className="table__row-el" key={prop.id}>
                                <p className="table__row-el-txt">{ editData[prop.id] }</p>
                            </li>
                        );
                    })
                }
                <li className="table__row-el">
                    <button className="table__crclBtn table__crclBtn_edit" onClick={ () => setEditable(!editable) }></button>
                </li>
            </ul>
        );
    }
}
