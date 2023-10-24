'use client';
import Table from "../components/table";


export default function Admin() {
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
        name: 'Логін',
        id: 1,
        req: true,
        type: 'text'
    },
    {
        name: 'Пароль',
        id: 2,
        req: true,
        type: 'password'
    },
];
