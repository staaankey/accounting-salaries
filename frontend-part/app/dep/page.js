'use client';
import TableDep from '../components/table_dep.js';


export default function Departments() {
    return <TableDep head={head} body={{}}></TableDep>;
}


const head = {
    main: [
        {
            name: 'Ідентифікатор',
            id: 0,
            dis: true,
            type: 'number'
        },
        {
            name: 'Назва',
            id: 1,
            dis: false,
            type: 'text'
        },
        {
            name: 'Ідентифікатор головного підрозділу',
            id: 2,
            dis: true,
            type: 'number'
        },
    ],
    empl: [
        {
            name: 'ПІБ',
            id: 0,
            dis: false,
            type: 'text'
        },
        {
            name: 'Фото',
            id: 1,
            dis: false,
            type: 'file'
        },
        {
            name: 'Телефон',
            id: 2,
            dis: false,
            type: 'tel'
        },
        {
            name: 'Адрес',
            id: 3,
            dis: false,
            type: 'text'
        },
        {
            name: 'РНОКПП',
            id: 4,
            dis: false,
            type: 'number'
        },
    ]
};