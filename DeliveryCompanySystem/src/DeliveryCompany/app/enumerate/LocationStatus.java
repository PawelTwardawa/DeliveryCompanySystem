/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.enumerate;

import DeliveryCompany.app.enumerate.DeliveryStatus;

/**
 *
 * @author Paweł
 */
public enum LocationStatus  {
    Doreczono("Doreczono"),
    PowrotDoMagazynu("Powrot do magazynu"),
    OdebranoOdNadawcy("Odebrano od nadawcy"),
    NieOdebranoOdNadawcy("nie odebrano od nadawcy"),
    DoOdebraniaOdNadawcy("Do odebrania od nadawcy");
    
    private final String type;
    
    LocationStatus(final String type)
    {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
