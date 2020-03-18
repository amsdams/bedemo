import { browser, by, element, promise } from 'protractor';

export class VerwachteWaardePensioenBerekeningListPage {
  navigateTo() {
    return browser.get(browser.baseUrl+'/toon-alle-verwachtewaardepensioenberekeningen') as Promise<any>;
  }

  getTitleText() {
    return element(by.css('app-root h2')).getText() as Promise<string>;
  }


   getLastRowCell() {
    return element.all(by.css('table tr td:last-child')).last().getText() as Promise<string>;

  }

  

  
}