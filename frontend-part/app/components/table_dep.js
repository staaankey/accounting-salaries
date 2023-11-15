import { useState } from "react";
import { useRef } from "react";
import { useImmer } from "use-immer";
import { v4 as uuidv4 } from 'uuid';
import { Splide, SplideSlide } from '@splidejs/react-splide';


export default function TableDep({ head, body }) {
    const [emplTable, setEmplTable] = useState(null);

    const currentInp = useRef({
        head: [],
        body: []
    }).current; // head.[main, empl].id
    const [mainRowData, updateMainRowData] = useImmer({});  // {uuidv4: [ body.main ],}
    const [emplRowData, updateEmplRowData] = useImmer({});  // { main.uuidv4: { uuidv4: [ body.empl ], }, }
    const [editRow, setEditRow] = useState(null);
    const [delRows, setDelRows] = useState([]);


    function handleTableChange(id) {
        setDelRows([]);
        setEditRow(null);
        emplTable ? setEmplTable(null) : setEmplTable(id);
    }

    function handleCurrentInp(e, inpVar) {
        currentInp[inpVar][ e.target.dataset.id ] = e.target.value;
    }
    
    function handleAddRow() {
        const newRow = uuidv4();

        if (emplTable) {
            updateEmplRowData(draft => {
                draft[emplTable][newRow] = [ ...currentInp.head ];
            });
        } else {
            updateMainRowData(draft => {
                draft[newRow] = [ ...currentInp.head ];
            });
            updateEmplRowData(draft => {
                draft[newRow] = {};
            })
        }
    }

    function handleEditMode(id) {
        if ( editRow === id ) {
            if (emplTable) { 
                updateEmplRowData(draft => {
                    draft[emplTable][id] = [ ...currentInp.body ];
                });
            }
            else {
                updateMainRowData(draft => {
                    draft[id] = [ ...currentInp.body ];
                });
            }
            setEditRow(null);

        } else {
            if (emplTable) { currentInp.body = [ ...emplRowData[emplTable][id] ] }
            else { currentInp.body = [ ...mainRowData[id] ] }
            setEditRow(id);
        }
    }

    function handleChooseRow(id) {
        if ( !delRows.includes(id) ) {
            setDelRows([ ...delRows, id ]);
        } else {
            const i = delRows.indexOf(id);
            setDelRows( delRows.toSpliced(i, 1) );
        }
    }

    function handleDelRow() {
        if (emplTable) {
            updateEmplRowData(draft => {
                for (let i in emplRowData[emplTable]) {
                    if ( delRows.includes(i) ) delete draft[emplTable][i];
                }
            });
        } else {
            updateMainRowData(draft => {
                for (let i in mainRowData) {
                    if ( delRows.includes(i) ) delete draft[i];
                }
            });
            updateEmplRowData(draft => {
                for (let i in emplRowData) {
                    if ( delRows.includes(i) ) delete draft[i];
                }
            });
        }

        setDelRows([]);
    }


    function rowCreator(isEdit, id, i, key, expVar) {
        function rowVariant(isEdit, id, objId, type, dis) {
            if (isEdit) return (
                <li className="table__row-el" key={objId}>
                    <input className={`table__row-el-inp ${ dis ? 'table__row-el-inp_dis' : '' }`} 
                        disabled={dis} onChange={ e => handleCurrentInp(e, 'body') }
                        placeholder={
                            emplTable ? emplRowData[emplTable][id][objId] : mainRowData[id][objId]
                        }  
                        data-id={objId} type={type} required={ !dis }>
                    </input>
                </li>
            );
            return (
                <li className={`table__row-el ${ emplTable ? '' : 'table__row-el_onHover' }`} 
                    key={objId}
                    onClick={function() {
                        if (emplTable) return false;
                        handleTableChange(id);
                }}>
                    <p className="table__row-el-txt">{
                        emplTable ? emplRowData[emplTable][id][objId] : mainRowData[id][objId]
                    }</p>
                </li>
            );
        }

        return (
            <ul className={`table__row ${ (delRows.includes(id) && !isEdit) ? 'table__row_onDel' : '' }`} 
                key={key} id={id}>
                {
                    head[tableVar].map(obj => {
                        if ( obj.id <= i && (obj.id >= i - elemsPerSlide) && expVar ) return (
                            rowVariant(isEdit, id, obj.id, obj.type, obj.dis)
                        );
                        else if ( (obj.id >= i) && !expVar ) return (
                            rowVariant(isEdit, id, obj.id, obj.type, obj.dis)
                        );
                    })
                }
                <li className="table__row-el table__delCheckbox table__delCheckbox_mrgin">
                    <input type="checkbox" checked={ delRows.includes(id) } 
                        onChange={ () => handleChooseRow(id) }>
                    </input>
                </li>
                <li className="table__row-el">
                    <button 
                        className={`table__crclBtn table__crclBtn_mrgin ${ isEdit ? 'table__crclBtn_onEdit' : 'table__crclBtn_edit' }`} 
                        onClick={ () => handleEditMode(id) }>
                    </button>
                </li>
            </ul>
        );
    }

    function tableCreator(i, key, expVar) {
        const rowDataVar = emplTable ? ( Object.keys( emplRowData[emplTable] ) ) : ( Object.keys(mainRowData) );
        function rowExpVar(id, dis, type) {
            return (
                <li key={id} className="table__row-el">
                    <input className={`table__row-el-inp ${ dis ? 'table__row-el-inp_dis' : '' }`} 
                        onChange={ e => handleCurrentInp(e, 'head') } 
                        data-id={id} type={type} required={ !dis }
                        disabled={dis}>
                    </input>
                </li>                
            );
        }
        

        return (
            <SplideSlide className="splide__slide table table_pdding" key={key}>
                <ul className="table__row">
                    {
                        head[tableVar].map(obj => { //  before-eq 4 
                            if ( obj.id <= i && (obj.id >= i - elemsPerSlide) && expVar ) return (
                                <li className="table__row-head" key={obj.id}>{obj.name}</li>
                            );
                            else if ( (obj.id >= i) && !expVar ) return <li className="table__row-head" key={obj.id}>{obj.name}</li>;
                        })
                    }
                </ul>
                <ul className="table__row">
                    {
                        head[tableVar].map(obj => {
                            if ( obj.id <= i && (obj.id >= i - elemsPerSlide) && expVar ) return (
                                rowExpVar(obj.id, obj.dis, obj.type)
                            );
                            else if ( (obj.id >= i) && !expVar ) return ( //  before-eq 4 
                                rowExpVar(obj.id, obj.dis, obj.type)
                            );
                        })
                    }
                    <li className="table__row-el">
                        <button type="submit" onClick={ (delRows.length > 0) ? handleDelRow : handleAddRow } 
                            className={`table__crclBtn table__crclBtn_mrgin ${ (delRows.length > 0) ? 'table__crclBtn_onDel' : 'table__crclBtn_onAdd' }`}>
                        </button>
                    </li>
                </ul>
                {
                    rowDataVar.map(row => {
                        if ( emplTable === row ) {
                            if ( editRow === row ) return (
                                rowCreator(true, row, i, uuidv4(), expVar)
                            );
                            return (
                                rowCreator(false, row, i, uuidv4(), expVar)
                            );
                        } else {
                            if ( editRow === row ) return (
                                rowCreator(true, row, i, uuidv4(), expVar)
                            );
                            return (
                                rowCreator(false, row, i, uuidv4(), expVar)
                            );
                        }
                    })
                }
            </SplideSlide>
        );
    }

    
    const tableVar = emplTable ? 'empl' : 'main';
    const slides = [];
    let tempJSX;
    const elemsPerSlide = 2; // elemPerSlide++, starts from 0
    for (let i = 0; i < head[tableVar].length; i++) {
        if ( i > 0 && i % elemsPerSlide === 0 ) {
            tempJSX = tableCreator( i, uuidv4(), true );
            slides.push(tempJSX);
        } else if ( i > elemsPerSlide && head[tableVar].length - 1 - i < elemsPerSlide ) {
            tempJSX = tableCreator( i, uuidv4(), false );
            slides.push(tempJSX);
            break;
        }
    }
    const resultJSX = (
        <Splide>
            {
                slides
            }
        </Splide>
    );


    if (emplTable) return (
        <>
            {
                resultJSX
            } 
            <button className="backBtn" onClick={handleTableChange}>back</button>
        </>
    );
    return resultJSX;
}