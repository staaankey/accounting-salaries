'use client';
import styles from './page.module.scss';
import Table from '../components/table.js';


export default function Departments() {
    return <Table colProps={column}></Table>;
}


const column = [
    {
        name: 'Ідентифікатор',
        id: 0,
        req: false,
        type: 'number'
    },
    {
        name: 'Назва',
        id: 1,
        req: true,
        type: 'text'
    },
    {
        name: 'Ідентифікатор головного підрозділу',
        id: 2,
        req: false,
        type: 'number'
    },
];
