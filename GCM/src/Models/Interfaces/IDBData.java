package Models.Interfaces;

import java.sql.ResultSet;

public interface IDBData
{
    //region Properties
    ResultSet ResultData = null;
    //endregion

    //region Methods
    void ClearData();
    ResultSet GetData();
    //endregion




}