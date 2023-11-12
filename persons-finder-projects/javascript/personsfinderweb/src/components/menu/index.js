import React from 'react';
import { Menubar } from 'primereact/menubar';
import useToken from '../../useToken';
import {
    MenuContainer,
    MenubarWrapper,
    MenubarContent,
    Menu,
    MenuItem,
    LogoutButton,
  } from './styles';

export default function MainMenu() {
    const { removeToken } = useToken();
    const items =
        [
            {
                label: 'Register Person',
                icon: 'pi pi-user-plus',
                command: (event) => {
                    window.location.href = "/registerperson";
                }
            },
            {
                label: 'List Persons\' Names',
                icon: 'pi pi-users',
                command: (event) => {
                    window.location.href = "/listpersons";
                }
            },
            {
                label: 'Set Location',
                icon: 'pi pi-map-marker',
                command: (event) => {
                    window.location.href = "/setlocation";
                }
            },
            {
                label: 'Find Around',
                icon: 'pi pi-search',
                command: (event) => {
                    window.location.href = "/findaround";
                }
            },
            {
                label: 'Sign out',
                icon: 'pi pi-sign-out',
                command: (event) => {
                    logout()
                }
            },
        ];

    function logout() {
        removeToken()
        window.location.reload(false)
    }
    return(
        <MenuContainer>
            <MenubarWrapper>
                <MenubarContent>
                <Menu>
                    <Menubar
                        model={items}
                        style={{
                            textAlign: 'left',
                            fontSize: '33px'
                    }}
                    />
                </Menu>
                {/* <MenuItem>
                    <LogoutButton onClick={logout}>Sign out</LogoutButton>
                </MenuItem> */}
                </MenubarContent>
            </MenubarWrapper>
        </MenuContainer>
    );
}