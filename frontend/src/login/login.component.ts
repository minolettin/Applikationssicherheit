import {css, html, LitElement} from 'lit';
import {customElement} from 'lit/decorators.js';

@customElement('as-login')
export class LoginComponent extends LitElement {
  static styles = css`p { color: blue }`;

  render() {
    return html`<p>Hello</p>`;
  }
}
