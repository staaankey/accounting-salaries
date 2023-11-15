import { useState } from "react";
import { useRef } from "react";
import { v4 as uuidv4 } from 'uuid';


export default function Table({ column }) {
    const [rows, setRows] = useState([]);
    const rowData = useRef({}).current;
    const [isChecked, setIsChecked] = useState(false);
    const [checkedBoxes, setCheckedBoxes] = useState([]);

    function handleChange(e) {
        rowData[e.target.id] = e.target.value;
    }
    function handleToggle(e) {
        if (checkedBoxes.length === 0 && e.target.checked) {
            setIsChecked(true);
            setCheckedBoxes( [...checkedBoxes, e.target.id] );
        } else if (checkedBoxes.length === 1 && !e.target.checked) {
            setIsChecked(false);
            setCheckedBoxes([]);
        } else if (e.target.checked) {
            setCheckedBoxes( [...checkedBoxes, e.target.id] );
        } else {
            const index = checkedBoxes.indexOf( e.target.id );
            setCheckedBoxes( checkedBoxes.toSpliced(index, 1) );
        }
    }
    function handleClick(e) {
        if (isChecked) {
            const filtredArr = rows.filter( (row) => !checkedBoxes.includes(row) );
            setRows(filtredArr);
            setCheckedBoxes([]);
            setIsChecked(false);
        } else {
            setRows( [uuidv4(), ...rows] )
        }
    }

    return (
        <main className="table">
            <ul className="table__row">
                {
                    column.map(prop => {
                        return <li className="table__row-head" key={prop.id}>{prop.name}</li>
                    })
                }
            </ul>
            <ul className="table__row">
                {
                    column.map(prop => {
                        return (
                            <li key={prop.id} className="table__row-el">
                                <input className={`table__row-el-inp ${ prop.dis ? 'table__row-el-inp_dis' : '' }`} onChange={handleChange} id={prop.id} type={prop.type} required={ !prop.dis } disabled={prop.dis}></input>
                            </li>
                        );
                    })
                }
                <li className="table__row-el">
                    <button type="submit" onClick={ handleClick } className={`table__crclBtn table__crclBtn_onAdd ${ isChecked ? 'table__crclBtn_onDel' : '' }`}></button>
                </li>
            </ul>
            {
                rows.map(row => {
                    return (
                        <div className={`table__dataCell ${ checkedBoxes.includes(row) ? 'table__dataCell_sel' : '' }`} key={row}>
                            <input onChange={ handleToggle } id={row} className="table__delCheckbox" type="checkbox"></input>
                            <TableRow column={column} rowData={rowData}></TableRow>
                        </div>
                    );
                })
            }
        </main>
    );
}

function TableRow({ column, rowData }) {
    const [editable, setEditable] = useState(false);
    const editData = useRef({...rowData}).current;

    function handleChange(e) {
        editData[e.target.id] = e.target.value;
    }

    if (editable) {
        return (
            <ul className="table__row">
                {
                    column.map(prop => {
                        return (
                            <li className="table__row-el" key={prop.id}>
                                <input className={`table__row-el-inp ${ prop.dis ? 'table__row-el-inp_dis' : '' }`} disabled={prop.dis} placeholder={ editData[prop.id] } onChange={handleChange} id={prop.id} type={prop.type} required={ !prop.dis }></input>
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
                    column.map(prop => {
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