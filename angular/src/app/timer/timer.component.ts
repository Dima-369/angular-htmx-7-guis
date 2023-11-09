import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html'
})
export class TimerComponent implements OnInit {

  /**
   * The max time in milliseconds the timer can run.
   */
  maxTimeMs: number = 10000

  /**
   * From 0 to maxTimeMs.
   */
  durationMs: number = 5000

  elapsedMs: number = 0

  /**
   * From 0 to maxTimeMs.
   */
  progressValueMs: number = 0;

  private lastTimestamp: number = 0

  updateOnAnimationFrame(timestamp: DOMHighResTimeStamp): void {
    this.elapsedMs = Math.min(this.elapsedMs + (timestamp - this.lastTimestamp), this.durationMs);
    if (this.durationMs === 0) {
      this.progressValueMs = this.maxTimeMs;
    } else {
      this.progressValueMs = (this.elapsedMs / this.durationMs) * this.maxTimeMs;
    }
    this.lastTimestamp = timestamp;
    requestAnimationFrame(this.updateOnAnimationFrame.bind(this))
  }

  formatElapsed(): string {
    return (this.elapsedMs / 1000).toFixed(1) + "s"
  }

  ngOnInit(): void {
    requestAnimationFrame(this.updateOnAnimationFrame.bind(this))
  }

  reset(): void {
    this.elapsedMs = 0
  }
}
