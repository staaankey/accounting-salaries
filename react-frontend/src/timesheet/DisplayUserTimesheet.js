import axios from 'axios'
import {useNavigate} from "react-router-dom";

export default function DisplayUserTimesheet() {
    let navigate = useNavigate();

    const showTimesheet = async () => {
        await axios.get("http://localhost:8080/displayTimesheet?month=9&username=Oleh&year=2023");
    };

}