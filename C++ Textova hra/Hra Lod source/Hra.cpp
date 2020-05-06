#include "Hra.h"

Hra::Hra() {
}

void Hra::menu() {
    string jmeno;
    int vyber;
    do {
        cout<<endl<<"  1. Nova hra     2.Pointy    3.Konec "<<endl<<endl<<"Vberte cislo: "; cin>>vyber;
       if(vyber > 0 && vyber < 3) {
         switch(vyber){
            case 1:
                m_mapa.push_back(new Mapa());
                cout<<endl<<"     Zadaj jmeno: "; cin>>jmeno; cout<<endl;
                m_hrac.push_back(new Lod(jmeno, new SilnaObrana(5000, 50))); //სიცოცხლე და ბონუსი
                m_mapa.at(m_mapa.size()-1)->menu(m_hrac.at(m_hrac.size()-1));
                break;
            case 2:
                for (int i=0; i<m_hrac.size(); i++){
                    cout<<"  "<<i<<"  "<<m_hrac.at(i)->getJmeno()<<"  "<<m_hrac.at(i)->getPoints()<<endl;
                }
                break;
        }
       }

    }while(vyber < 1 || vyber < 3);
}

void Hra::novaHra() {
    menu();
}

void Hra::ukazPointy() {

}

void Hra::pridejHrace(Lod* lod) {

}

Lod* Hra::getHrace(int ktery) {

}

int Hra::getPocetHracu() {

}

Hra::~Hra() {

}
