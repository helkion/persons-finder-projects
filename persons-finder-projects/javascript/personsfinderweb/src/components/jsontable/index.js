import React from 'react';

const JsonTable = ({ data }) => {
  const keys = Object.keys(data[0]);

  return (
    <table>
      <thead>
        <tr>
          {keys.map((key, index) => (
            <th key={index}>{key}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((item, index) => (
          <tr key={index}>
            {keys.map((key, index) => (
              <td key={index}>{item[key]}</td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default JsonTable;
