
import { browser, logging } from 'protractor';
import { JaarlijksePremieStortingBerekeningFormPage } from './jaarlijkse-premie-storting-berekening-form.po';
import { JaarlijksePremieStortingBerekeningListPage } from './jaarlijkse-premie-storting-berekening-list.po';

describe('workspace-project JaarlijksePremieStortingBerekeningFormPage', () => {
  let page: JaarlijksePremieStortingBerekeningFormPage;
  let pageResult: JaarlijksePremieStortingBerekeningListPage;
  const timeout = 1000;

  beforeEach(() => {
    page = new JaarlijksePremieStortingBerekeningFormPage();
    pageResult = new JaarlijksePremieStortingBerekeningListPage();
  });

  it('should display BeDemo', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('BeDemo');
  });

  it('Should berekenen #1', () => {
    page.navigateTo();

    page.getVoltijdSalarisInput().sendKeys('40000.00');
    page.getDeeltijdPercentageInput().sendKeys('100.00');
    page.getVoltijdFranchiseInput().sendKeys('13785.00');
    page.getPremiePercentageInput().sendKeys('1.657');

    page.getBerekenButton().click();

    browser.driver.wait(function() {
      return browser.driver.getCurrentUrl().then(function(url) {
        return (/toon-alle-jaarlijksepremiestortingberekeningen/).test(url);
      });
    }, timeout).then(function() {
      return;
    });
    expect(browser.getCurrentUrl()).toContain('/toon-alle-jaarlijksepremiestortingberekeningen');


    // pageResult.navigateTo();

    expect(pageResult.getLastRowCell()).toEqual('434.38255000000004');
  });

  it('Should berekenen #2', () => {
    page.navigateTo();

    page.getVoltijdSalarisInput().sendKeys('40000.00');
    page.getDeeltijdPercentageInput().sendKeys('100.00');
    page.getVoltijdFranchiseInput().sendKeys('15599.00');
    page.getPremiePercentageInput().sendKeys('5.00');

    page.getBerekenButton().click();

    browser.driver.wait(function() {
      return browser.driver.getCurrentUrl().then(function(url) {
        return (/toon-alle-jaarlijksepremiestortingberekeningen/).test(url);
      });
    }, timeout).then(function() {
      return;
    });
    expect(browser.getCurrentUrl()).toContain('/toon-alle-jaarlijksepremiestortingberekeningen');


    // pageResult.navigateTo();

    expect(pageResult.getLastRowCell()).toEqual('1220.05');
  });



  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
