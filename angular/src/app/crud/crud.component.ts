import {Component} from '@angular/core';

class CrudEntry {

  /**
   * Unique ID.
   */
  id: number = -1
  name: string = ""
  surname: string = ""
}

@Component({
  selector: 'app-crud',
  templateUrl: './crud.component.html',
  styleUrls: ['./crud.component.css']
})
export class CrudComponent {
  filter: string = "";
  name: string = "";
  surname: string = "";
  entries: CrudEntry[] = [
    {id: 0, name: "Hans", surname: "Emil"},
    {id: 1, name: "Max", surname: "Mustermann"},
    {id: 2, name: "Roman", surname: "Tisch"}
  ]
  selectedEntry: CrudEntry | null = null

  update(): void {
    const selected: CrudEntry | undefined = this.entries.find((entry: CrudEntry) =>
      entry.id === this.selectedEntry!.id
    )
    if (selected) {
      selected.name = this.name
      selected.surname = this.surname
    }
  }

  private getNewId() {
    let highestIndex: number = 0
    for (let i: number = 0; i < this.entries.length; i++) {
      const entry: CrudEntry = this.entries[i]
      if (entry.id > highestIndex) {
        highestIndex = entry.id
      }
    }
    return highestIndex + 1
  }

  create(): void {
    if (this.name != "" && this.surname != "") {
      this.entries.push({id: this.getNewId(), name: this.name, surname: this.surname})
    }
  }

  delete(): void {
    this.entries = this.entries.filter((entry: CrudEntry) => entry.id !== this.selectedEntry!.id)
    this.selectedEntry = null
    this.name = ""
    this.surname = ""
  }

  clickEntry(entry: CrudEntry): void {
    this.selectedEntry = entry
    this.name = entry.name
    this.surname = entry.surname
  }

  getFilteredEntries(): CrudEntry[] {
    if (this.filter == "") {
      return this.entries
    }

    const lowerFilter: string = this.filter.toLowerCase()
    if (this.selectedEntry) {
      return this.entries.filter((entry: CrudEntry) =>
        entry.id === this.selectedEntry!.id || entry.surname.toLowerCase().startsWith(lowerFilter))
    }

    return this.entries.filter((entry: CrudEntry) =>
      entry.surname.toLowerCase().startsWith(lowerFilter))
  }

  unsetSelected(): void {
    this.selectedEntry = null
    this.name = ""
    this.surname = ""
  }
}
