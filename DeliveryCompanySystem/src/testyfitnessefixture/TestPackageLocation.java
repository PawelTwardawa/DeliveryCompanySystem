/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testyfitnessefixture;

import fit.ColumnFixture;

/**
 *
 * @author Paweł
 */
public class TestPackageLocation extends ColumnFixture {
    
    long id;
    
    public String getPackageLocation()
    {
        return SetUp.clientFunc.getPackageLocation(id);
    }
}
