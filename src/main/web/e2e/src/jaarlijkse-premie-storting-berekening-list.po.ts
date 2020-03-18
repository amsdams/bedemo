import { browser, by, element, promise } from 'protractor';

export class JaarlijksePremieStortingBerekeningListPage {
  navigateTo() {
    return browser.get(browser.baseUrl+'/toon-alle-jaarlijksepremiestortingberekeningen') as Promise<any>;
  }

  getTitleText() {
    return element(by.css('app-root h2')).getText() as Promise<string>;
  }
/*voltijdSalaris?: number;
	deeltijdPercentage?: number;
	voltijdFranchise?: number;
    premiePercentage?: number;
    jaarlijksePremieStortingResultaat?: number;
    */

   getLastRowCell() {
    return element.all(by.css('table tr td:last-child')).last().getText() as Promise<string>;

  }

  

  
}