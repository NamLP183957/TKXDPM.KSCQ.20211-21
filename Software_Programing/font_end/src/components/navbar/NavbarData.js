import React from 'react'
import * as FaIcons from 'react-icons/fa'
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import {MdOutlineDirectionsBike} from 'react-icons/md'
import {FaKey} from 'react-icons/fa'

export const NavbarData = [
    {
        title: 'Bãi đậu xe',
        path: '/station/',
        icon: <AiIcons.AiFillHome />,
        cName: 'nav-text'
    },
    {
        title: 'Thuê xe',
        path: '/rent-bike/',
        icon: <FaKey />,
        cName: 'nav-text'
    },
    {
        title: 'Xe đang thuê',
        path: '/renting-return-bike/',
        icon: <MdOutlineDirectionsBike />,
        cName: 'nav-text'
    }
]