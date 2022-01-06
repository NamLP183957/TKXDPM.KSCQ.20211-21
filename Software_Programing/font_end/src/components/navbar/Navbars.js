import React from 'react'
import { IconContext } from 'react-icons/lib'
import { Link } from 'react-router-dom'
import { NavbarData } from './NavbarData'
import './Navbar.css'
function Navbars() {
    return (
        <div>
            <IconContext.Provider value={{ color: '#fff' }}>
                <nav className="nav-menu active">
                    <ul className="nav-menu-items">
                        {NavbarData.map((item, index) => (
                            <li key={index} className={item.cName}>
                                <Link to={item.path}>
                                    {item.icon}
                                    <span>{item.title}</span>
                                </Link>
                            </li>
                        ))}
                    </ul>
                </nav>
            </IconContext.Provider>
        </div>
    )
}

export default Navbars
