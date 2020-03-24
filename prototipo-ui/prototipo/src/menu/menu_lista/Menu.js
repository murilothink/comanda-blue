import React from "react";
import "./css/Menu.css";

export default class MenuList extends React.Component {
    render() {
        return (
            <div className="MenuList">
                <table>
                    <ol className="coll">
                        <li>
                            <img src="./img/download.png" />
                        </li>
                    </ol>
                </table>

            </div>
        );
    }
}
