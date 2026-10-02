package strategia;

import java.util.List;

import dominio.Attivita;

//interfaccia per le strategie di ordinamento delle attivitàgit gg
public interface StrategiaOrdinamento {
    public void ordina(List<Attivita> lista);
    
}
