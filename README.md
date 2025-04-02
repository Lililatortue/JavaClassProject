# JavaClassProject
# situation
Fortis Bank est une institution financière qui souhaite moderniser ses opérations bancaires à travers un système bancaire numérique sécurisé. Actuellement, la banque rencontre plusieurs défis dans la gestion des comptes clients, des transactions et services bancaires. Le traitement de certaines opérations entraîne des inefficacités, des délais longs et des coûts d’exploitation accrus. De plus, les gestionnaires de la banque éprouvent des difficultés dans la création, la modification et la fermeture des comptes. Pour remédier à ces problèmes, Fortis Bank a décidé de développer une application bancaire modulaire centralisée qui simplifiera l’intégration des clients, la gestion des comptes clients ainsi que les transactions effectuées par ces derniers Ce système permettra au gestionnaire de la banque de mettre à jour efficacement les clients de la banque ainsi que les comptes qui leur sont associés. De plus, ce système offrira aux clients une interface utilisateur conviviale leur permettant d’effectuer les opérations bancaires telles que les dépôts, les retraits, les virements, les transferts d’un compte vers un autre compte et la consultation de leur solde ainsi que l’historique des transactions 

# fonctionnalite
## 1 Gestion des clients
- [x]  client doit avoir  numero d'identifaction (clef primaire) , prenom , nom , nip(psw)
- [x]  client doit avoir d' autres formes d'identification e.g adresse , email et num tel.

## 2 Gestion des comptes client
- [x] compte cheques obligatoire pour chaque client
- #### compte optionnel pour le clien a integrer:
- [x] compte epargne --> contient taux interet annuel et calcul du gain annuel
- [x] compte credit ---> limite et emprunt
- [x] ligne de credit --> taux d'interet et un mecanisme emprunt et remboursement
- [x] Compte devise ---> doit rester actif a tous les ans

## 3 traitement des transactions
- [ ] type de transaction disponible--> depot retrait virement
- [ ] compte a un historique
- [x] compte cheque 2 transaction gratuit par moi
- [ ] frais applique si transaction depasse quota

## 4 Operation du gestionnaire de la banque
le role de gestionnaire de la banque peux faire
- [ ] creer client
- [ ] approuver les demandes de comptes supplementaire
- [ ] fermer des comptes client
- [ ] et si fermer compte cheque effacer le client

## 5 Operations des clients
client peux faire:
- [ ] transaction
- [ ] consulter le solde
- [ ] consulter historique

## 6 Gestion des interets et du credit
- [x] compte epargne genere interet annuel

## 7 Notification & Alertes en temps reel
- [ ] notifier le client quand transaction est effectue ou soldes est faible
- [ ] notifier gestionnaire quand demande compte est faite ou tombe en dessous du seuil critique

## 8 Rapport Financiers et Audits
- [ ] gestionnaire accede au log des clients
- [ ] gestionnaire accede au log des transactions
- [ ] gestionnaire accede au log des comptes
- [ ] creation de rapport auto genere 
## 9 evolutivite & architecture modulaire
- [x] Enforcer les principes SOLID
- [x] utilisation des patterns ou ces necessaires
- [x] decouplement du ui, bus et dal
