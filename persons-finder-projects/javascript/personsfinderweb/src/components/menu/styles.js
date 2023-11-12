import styled from 'styled-components';

export const MenuContainer = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;

export const MenubarWrapper = styled.div`
  margin-left: auto;
  margin-right: auto;
  max-width: 800px; 
  width: 100%;
`;

export const MenubarContent = styled.div`
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;

export const Menu = styled.div`
  align-items: flex-start;

  .p-menubar .p-menubar-root-list {
    list-style-type: none; 
  }
`;

export const MenuItem = styled.div`
  margin-bottom: 10px;
`;

export const LogoutButton = styled.button`
  background-color: #d9534f; 
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 13px;
  transition: background-color 0.3s;

  &:hover {
    background-color: #c9302c; 
  }
`;