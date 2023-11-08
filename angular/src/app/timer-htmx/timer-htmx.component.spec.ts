import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimerHtmxComponent } from './timer-htmx.component';

describe('TimerHtmxComponent', () => {
  let component: TimerHtmxComponent;
  let fixture: ComponentFixture<TimerHtmxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TimerHtmxComponent]
    });
    fixture = TestBed.createComponent(TimerHtmxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
