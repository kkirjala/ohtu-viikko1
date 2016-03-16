package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    
    @Test
    public void addMoreThanCapacity() {
        
        // capacity == 10
        varasto.lisaaVarastoon(20);
        
        // capacity should equal stock level, the rest will be thrown away
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void addNegativeQuantity() {
    
        double quantityOrig = varasto.getSaldo();
        
        // should not cause any kind of addition
        
        varasto.lisaaVarastoon(-2);
        
        assertEquals(quantityOrig, varasto.getSaldo(), vertailuTarkkuus);
    }
    

    @Test
    public void takeNegativeQuantity() {
    
        double quantityOrig = varasto.getSaldo();
        
        // should not cause any kind of addition
        
        varasto.otaVarastosta(-2);
        
        assertEquals(quantityOrig, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void takeMoreThanStockLevel() {        
        
        // we'll try to take 5 units more than stock level, should only give
        // us as much as the stock level is on a specific moment.
        double quantityOrig = varasto.getSaldo();        
        double quantityTake = quantityOrig + 5;
        
        assertEquals(quantityOrig + 2, varasto.otaVarastosta(quantityTake), vertailuTarkkuus);
        
    }
    
    
    

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}