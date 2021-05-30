Projet de programmation mobile de 3ème année de Samy ZERARKA

Final Space App est une application qui recense les personnages de la série Final Space.
Cette application oscille entre 2 écrans:
        - une liste des personnages implémentée dans une recyclerview
        - un écran qui affiche les détails du personnage choisi, dont une photo téléchargée depuis une URL
        
Les données sont récupérées de l'API Rest Final Space : https://finalspaceapi.com/
plus précisément dans l'endpoint "/api/v0/character"

Tous les appels à l'API Rest sont fait par un Singletons.

Un bug important doit être recensé ici, il est lié d'une quelconque façon au premier démarrage de l'appli, 
et plus précisément au premier appel de la fonction qui télécharge les photos grâce aux URL et un inputStream :

        Au premier démarrage, la liste s'affiche correctement, et quand on clique sur un personnage, la fonction qui télécharge la photo associée à l'url fait crasher l'appli.
        Au second démarrage, tout fonctionne. 
        
        Je ne sais pas si c'est car j'ai débuggué sur mon propre téléphone et qu'il y a des mesures de sécurités de 1ère connexion,
        ou si ce résultat aura lieux sur tous les appareils.
        
L'application est codée pour s'utiliser en mode portrait, bien que les contraintes screenOrentation aient été mises dans les fichiers XML, 
l'application est tout de même sujette à se tourner en mode paysage quand on oriente le téléphone pour ça.
