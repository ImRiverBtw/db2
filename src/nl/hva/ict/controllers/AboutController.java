package nl.hva.ict.controllers;

import nl.hva.ict.views.AboutView;
import nl.hva.ict.views.View;

/**
 * Altijd leuk een about pagina
 * @author HBO-ICT
 */
public class AboutController extends Controller {

    private final AboutView aboutView;

    /**
     * Constructor welke direct een instance maakt van de view
     */
    public AboutController() {
        aboutView = new AboutView();
    }


    /**
     * Methode om de view door te geven zoals dat ook bij OOP2 ging
     * @return View
     */
    @Override
    public View getView() {
        return aboutView;
    }
}
