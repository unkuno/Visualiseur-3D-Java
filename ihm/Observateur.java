package ihm;

/*
 * Deux interfaces pour l'implantation du patron de conception Observateur.
 * Reference: http://www.design-patterns.fr/, par exemple.
 */

/**
 * Patron "Observateur": interface d'un objet souhaitant être écouté
 * par des observateurs.
 */
interface Observable {
	/** Enregistre un nouvel Observateur. */
	void ajouteObservateur(Observateur obs);
	/** Notifie les observateurs enregistrés d'un changement d'état. */
	void notifieObservateurs();
}


/**
 * Patron "Observateur": interface d'un objet écoutant un observable.
 */
interface Observateur {
	/** Méthode appelée suite à un changement d'état de l'objet observé. */
	void actualise();
}
