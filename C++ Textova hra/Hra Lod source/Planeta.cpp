#include "Planeta.h"
#include <windows.h>

Planeta::Planeta(string nazev, int cenaZaProdej, int cenaZaNakup, Pirat* pirat) {
	m_nazev = nazev;
	m_cenaZaProdej = cenaZaProdej;
	m_cenaZaNakup = cenaZaNakup;
	m_pirat = pirat;
	m_lod = nullptr;
}

string Planeta::getNazev() {
	return m_nazev;
}

Suroviny* Planeta::getSurovina(int kolikata) {
	return m_suroviny.at(kolikata);
}

Lod* Planeta::getLod() {
	return m_lod;
}

Pirat* Planeta::getPirat() {
	return m_pirat;
}

int Planeta::getCenaZaProdej() {
	return m_cenaZaProdej;
}

int Planeta::getCenaZaNakup() {
	return m_cenaZaNakup;
}

void Planeta::setLod(Lod* lod) {
	m_lod = lod;
}

void Planeta::pridejSurovinu(Suroviny* surovina) {
	m_suroviny.push_back(surovina);
}

void Planeta::odeberSurovinu(int kolikata) {
	m_suroviny.erase(m_suroviny.begin()+kolikata);
}

void Planeta::boj(Lod* lod) {
    cout<<endl<<"!!! Napadl ti pirat  :( "<<endl<<endl;
    do {
         m_pirat->getObrana()->odeberZivot(lod->getZbran()->getSila());
         lod->getObrana()->odeberZivot(m_pirat->getZbran()->getSila());
    } while (lod->getObrana()->getZivot() > 0 && m_pirat->getObrana()->getZivot() > 0);

    if ((m_pirat->getObrana()->getZivot() <= 0) && (lod->getObrana()->getZivot() > 0)){
        lod->pridejPenize(m_pirat->getBonus());
        lod->pridejPoints();
    }
}

void Planeta::nakupSurovin() {
    if (getLod() != nullptr){
        int a;
        if (m_suroviny.size()>0){
                cout<<endl<<"Prodejni cena: "<< m_cenaZaProdej << endl;
                cout<<"Nakupni cena: "<<m_cenaZaNakup<<endl<<endl;
            for (int i=0; i<m_suroviny.size(); i++){
                cout<<"  "<<i<<"  ";
                getSurovina(i)->printInfo();
                cout<<endl;
            }

            cout<<"Jakou surovinu chces koupit? ";
            cin>>a;
            if (a>=0 || a<m_suroviny.size()){
                if (getLod()->getPenize() >= m_cenaZaProdej) {
                    if (getLod()->getInventar()->getVelikost()-1 >= 0){
                        getLod()->odeberPenize(m_cenaZaProdej);
                        getLod()->getInventar()->pridejSurovinu(getSurovina(a));
                        odeberSurovinu(a);
                        cout<<endl<<"******************************"<<endl
                        <<"Uspesne nakoupil surovinu !!! "<<endl
                        <<"********************************"<<endl;

                        Beep(523,200);Beep(523,200);Beep(523,200);Beep(923,100);

                        getLod()->pridejPoints();
                    } else {
                        cout<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
                        cout<<"Nestaci volneho mista!"<<endl;
                        cout<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
                    }
                } else {
                    cout<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
                    cout<<"Nemas dost penez! "<<endl;
                    cout<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
                }
            } else {
                cout<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
                cout<<"Jsi zadal spatne cislo..."<<endl;
                cout<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
            }

        } else {
            cout<<endl<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
            cout<<endl<<"Zadna surovina na prodej ! "<<endl;
            cout<<endl<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
            Beep(823,200);Beep(823,200);Beep(823,200);Beep(423,900);Beep(623,200);Beep(623,200);Beep(623,200);Beep(323,500);
        }
    } else {
        cout<<endl<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
        cout<<"Nejsi na planete, a nemuzes nakupovat. Let' na planetu."<<endl;
        cout<<endl<<"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"<<endl;
    }
}

void Planeta::prodejSurovin() {
	if (getLod() != nullptr){
    int a;
        if (getLod()->getInventar()->getPocetSurovin() > 0){
            for (int i=0; i<getLod()->getInventar()->getPocetSurovin(); i++){
                cout<<i;
                getLod()->getInventar()->getSurovina(i)->printInfo();
                cout<<endl;
            }
            cout<<endl<<"??????????????????????????????"<<endl;
            cout<<endl<<"Kterou surovinu chces prodat? "<<endl;
            cout<<endl<<"??????????????????????????????"<<endl;
            cin>>a;
            if (a>=0 || a < getLod()->getInventar()->getPocetSurovin()){
                getLod()->pridejPenize(m_cenaZaNakup);
                pridejSurovinu(getLod()->getInventar()->getSurovina(a));
                getLod()->getInventar()->odeberSurovinu(a);
                cout<<"Jsi prodal surovinu ! "<<endl;
                getLod()->pridejPoints();
            } else {
                cout<<endl<<endl;
                cout<<"Jsi zadal spatne cislo   :( "<<endl<<endl;
            }
        }
         else {
            cout<<endl<<endl;
            cout<<"Mas prazdny inventar! "<<endl;
            cout<<endl<<endl;
        }

	} else {
	    cout<<"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"<<endl;
        cout<<"Nejsi na planete, a nemuzes prodavat"<<endl;
        cout<<"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"<<endl;
	}
}

void Planeta::menu(Lod* lod) {
    int krok;

    do {
       cout<<endl<<endl<<"  Nazev planety: "<<m_nazev<<endl;
       cout<<"  1. Info o planete: "<<endl;
       cout<<"  2. Letet na planetu "<<endl;
       cout<<"  3. Nakupuj: "<<endl;
       cout<<"  4. Prodej "<<endl;
       cout<<"  5. Info o sobe"<<endl;
       cout<<"  6. Odlet z planety "<<endl;

       cout<<" Vyber dalsi krok...      ";  cin>>krok;

       if(krok > 0 && krok < 6){
            switch(krok){
                case 1:
                    printInfo();
                    break;
                case 2:
                    boj(lod);
                    if(lod->getObrana()->getZivot() > 0){
                        setLod(lod);
                        cout << "Priletel jsi na planete" << endl;
                    } else {
                        cout << "Pirat te porazil" << endl;
                        lod->printInfo();
                    }
                    break;
                case 3:
                    nakupSurovin();
                    break;
                case 4:
                    prodejSurovin();
                    break;
                case 5:
                    lod->printInfo();
                    break;
           }
       }

    } while(krok <1 || krok < 6 || lod->getObrana()->getZivot() < 0);
    setLod(nullptr);
}

void Planeta::printInfo() {
	cout<<"Nazev planety: "<<m_nazev<<endl;
	cout<<"Podejni cena: "<<m_cenaZaProdej<<endl;
	cout<<"Nakupni cena: "<<m_cenaZaNakup<<endl;
	for (int i=0; i<m_suroviny.size(); i++){
        cout<<i<<endl;
        getSurovina(i)->printInfo();
        cout<<endl;
    }
}
