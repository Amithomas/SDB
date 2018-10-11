import { ElementRef, AfterContentInit, AfterViewInit, AfterViewChecked, OnInit, OnDestroy, IterableDiffers, EventEmitter, Renderer2, QueryList, TemplateRef, ChangeDetectorRef, NgZone } from '@angular/core';
import { Column, HeaderColumnGroup, FooterColumnGroup, PrimeTemplate } from '../common/shared';
import { LazyLoadEvent } from '../common/lazyloadevent';
import { FilterMetadata } from '../common/filtermetadata';
import { SortMeta } from '../common/sortmeta';
import { DomHandler } from '../dom/domhandler';
import { ObjectUtils } from '../utils/objectutils';
import { Subscription } from 'rxjs';
import { BlockableUI } from '../common/blockableui';
export declare class DTRadioButton {
    checked: boolean;
    onClick: EventEmitter<any>;
    hover: boolean;
    handleClick(event: any): void;
}
export declare class DTCheckbox {
    checked: boolean;
    disabled: boolean;
    onChange: EventEmitter<any>;
    hover: boolean;
    handleClick(event: any): void;
}
export declare class ColumnHeaders {
    dt: DataTable;
    constructor(dt: DataTable);
    columns: Column[];
}
export declare class ColumnFooters {
    dt: DataTable;
    constructor(dt: DataTable);
    columns: Column[];
}
export declare class TableBody {
    dt: DataTable;
    constructor(dt: DataTable);
    columns: Column[];
    data: any[];
    visibleColumns(): Column[];
}
export declare class ScrollableView implements AfterViewInit, AfterViewChecked, OnDestroy {
    dt: DataTable;
    domHandler: DomHandler;
    el: ElementRef;
    renderer: Renderer2;
    zone: NgZone;
    constructor(dt: DataTable, domHandler: DomHandler, el: ElementRef, renderer: Renderer2, zone: NgZone);
    columns: Column[];
    headerColumnGroup: HeaderColumnGroup;
    footerColumnGroup: HeaderColumnGroup;
    scrollHeaderViewChild: ElementRef;
    scrollHeaderBoxViewChild: ElementRef;
    scrollBodyViewChild: ElementRef;
    scrollTableViewChild: ElementRef;
    scrollTableWrapperViewChild: ElementRef;
    scrollFooterViewChild: ElementRef;
    scrollFooterBoxViewChild: ElementRef;
    frozen: boolean;
    width: string;
    virtualScroll: boolean;
    onVirtualScroll: EventEmitter<any>;
    scrollBody: HTMLDivElement;
    scrollHeader: HTMLDivElement;
    scrollHeaderBox: HTMLDivElement;
    scrollTable: HTMLDivElement;
    scrollTableWrapper: HTMLDivElement;
    scrollFooter: HTMLDivElement;
    scrollFooterBox: HTMLDivElement;
    bodyScrollListener: Function;
    headerScrollListener: Function;
    scrollBodyMouseWheelListener: Function;
    scrollFunction: Function;
    rowHeight: number;
    scrollTimeout: any;
    ngAfterViewInit(): void;
    ngAfterViewChecked(): void;
    initScrolling(): void;
    onBodyScroll(event: any): void;
    setScrollHeight(): void;
    onHeaderScroll(event: any): void;
    hasVerticalOverflow(): boolean;
    alignScrollBar(): void;
    ngOnDestroy(): void;
}
export declare class DataTable implements AfterViewChecked, AfterViewInit, AfterContentInit, OnInit, OnDestroy, BlockableUI {
    el: ElementRef;
    domHandler: DomHandler;
    differs: IterableDiffers;
    renderer: Renderer2;
    changeDetector: ChangeDetectorRef;
    objectUtils: ObjectUtils;
    zone: NgZone;
    paginator: boolean;
    rows: number;
    pageLinks: number;
    rowsPerPageOptions: number[];
    responsive: boolean;
    stacked: boolean;
    selectionMode: string;
    selectionChange: EventEmitter<any>;
    editable: boolean;
    showHeaderCheckbox: boolean;
    onRowClick: EventEmitter<any>;
    onRowSelect: EventEmitter<any>;
    onRowUnselect: EventEmitter<any>;
    onRowDblclick: EventEmitter<any>;
    onHeaderCheckboxToggle: EventEmitter<any>;
    headerCheckboxToggleAllPages: boolean;
    onContextMenuSelect: EventEmitter<any>;
    filterDelay: number;
    lazy: boolean;
    onLazyLoad: EventEmitter<any>;
    resizableColumns: boolean;
    columnResizeMode: string;
    onColResize: EventEmitter<any>;
    reorderableColumns: boolean;
    onColReorder: EventEmitter<any>;
    scrollable: boolean;
    virtualScroll: boolean;
    scrollHeight: any;
    scrollWidth: any;
    frozenWidth: any;
    unfrozenWidth: any;
    style: any;
    styleClass: string;
    tableStyle: any;
    tableStyleClass: string;
    globalFilter: any;
    sortMode: string;
    defaultSortOrder: number;
    groupField: string;
    contextMenu: any;
    csvSeparator: string;
    exportFilename: string;
    emptyMessage: string;
    paginatorPosition: string;
    alwaysShowPaginator: boolean;
    metaKeySelection: boolean;
    rowTrackBy: Function;
    immutable: boolean;
    frozenValue: any[];
    compareSelectionBy: string;
    onEditInit: EventEmitter<any>;
    onEditComplete: EventEmitter<any>;
    onEdit: EventEmitter<any>;
    onEditCancel: EventEmitter<any>;
    onPage: EventEmitter<any>;
    onSort: EventEmitter<any>;
    onFilter: EventEmitter<any>;
    header: any;
    footer: any;
    expandableRows: boolean;
    expandedRows: any[];
    expandableRowGroups: boolean;
    rowExpandMode: string;
    expandedRowsGroups: any[];
    expandedIcon: string;
    collapsedIcon: string;
    tabindex: number;
    rowStyleClass: Function;
    rowStyleMap: Object;
    rowGroupMode: string;
    sortableRowGroup: boolean;
    sortFile: string;
    rowHover: boolean;
    filters: {
        [s: string]: FilterMetadata;
    };
    dataKey: string;
    loading: boolean;
    loadingIcon: string;
    virtualScrollDelay: number;
    rowGroupExpandMode: string;
    valueChange: EventEmitter<any[]>;
    firstChange: EventEmitter<number>;
    onRowExpand: EventEmitter<any>;
    onRowCollapse: EventEmitter<any>;
    onRowGroupExpand: EventEmitter<any>;
    onRowGroupCollapse: EventEmitter<any>;
    templates: QueryList<PrimeTemplate>;
    cols: QueryList<Column>;
    headerColumnGroups: QueryList<HeaderColumnGroup>;
    footerColumnGroups: QueryList<FooterColumnGroup>;
    _value: any[];
    dataToRender: any[];
    page: number;
    filterTimeout: any;
    filteredValue: any[];
    columns: Column[];
    frozenColumns: Column[];
    scrollableColumns: Column[];
    frozenHeaderColumnGroup: HeaderColumnGroup;
    scrollableHeaderColumnGroup: HeaderColumnGroup;
    frozenFooterColumnGroup: HeaderColumnGroup;
    scrollableFooterColumnGroup: HeaderColumnGroup;
    columnsChanged: boolean;
    sortColumn: Column;
    columnResizing: boolean;
    lastResizerHelperX: number;
    documentEditListener: Function;
    documentColumnResizeEndListener: Function;
    resizerHelper: any;
    resizeColumn: any;
    reorderIndicatorUp: any;
    reorderIndicatorDown: any;
    iconWidth: number;
    iconHeight: number;
    draggedColumn: any;
    dropPosition: number;
    tbody: any;
    rowTouched: boolean;
    rowGroupToggleClick: boolean;
    editingCell: any;
    virtualTableHeight: number;
    rowGroupMetadata: any;
    rowGroupHeaderTemplate: TemplateRef<any>;
    rowGroupFooterTemplate: TemplateRef<any>;
    rowExpansionTemplate: TemplateRef<any>;
    emptyMessageTemplate: TemplateRef<any>;
    paginatorLeftTemplate: TemplateRef<any>;
    paginatorRightTemplate: TemplateRef<any>;
    scrollBarWidth: number;
    editorClick: boolean;
    _first: number;
    selectionKeys: any;
    preventSelectionKeysPropagation: boolean;
    preventSortPropagation: boolean;
    preventRowClickPropagation: boolean;
    _multiSortMeta: SortMeta[];
    _sortField: string;
    _sortOrder: number;
    differ: any;
    _selection: any;
    _totalRecords: number;
    globalFilterFunction: any;
    columnsSubscription: Subscription;
    totalRecordsChanged: boolean;
    anchorRowIndex: number;
    rangeRowIndex: number;
    initialized: boolean;
    virtualScrollTimer: any;
    virtualScrollableTableWrapper: HTMLDivElement;
    virtualScrollCallback: Function;
    editChanged: boolean;
    constructor(el: ElementRef, domHandler: DomHandler, differs: IterableDiffers, renderer: Renderer2, changeDetector: ChangeDetectorRef, objectUtils: ObjectUtils, zone: NgZone);
    ngOnInit(): void;
    ngAfterContentInit(): void;
    ngAfterViewChecked(): void;
    ngAfterViewInit(): void;
    multiSortMeta: SortMeta[];
    sortField: string;
    sortOrder: number;
    value: any[];
    first: number;
    totalRecords: number;
    selection: any;
    ngDoCheck(): void;
    handleDataChange(): void;
    initColumns(): void;
    initScrollableColumns(): void;
    initColumnGroups(): void;
    resolveFieldData(data: any, field: string): any;
    updateRowGroupMetadata(): void;
    updatePaginator(): void;
    updateTotalRecords(): void;
    onPageChange(event: any): void;
    paginate(): void;
    updateDataToRender(datasource: any): void;
    onVirtualScroll(event: any): void;
    onHeaderKeydown(event: any, column: Column): void;
    onHeaderMousedown(event: any, header: any): void;
    sort(event: any, column: Column): void;
    sortSingle(): void;
    sortMultiple(): void;
    multisortField(data1: any, data2: any, multiSortMeta: any, index: any): any;
    addSortMeta(meta: any): void;
    isSorted(column: Column): boolean;
    getSortOrder(column: Column): number;
    onRowGroupClick(event: any): void;
    clearSelectionRange(event: MouseEvent): void;
    selectRange(event: MouseEvent, rowIndex: number): void;
    handleRowClick(event: MouseEvent, rowData: any, index: number): void;
    handleRowTouchEnd(event: Event): void;
    selectRowWithRadio(event: Event, rowData: any): void;
    toggleRowWithCheckbox(event: any, rowData: any): void;
    toggleRowsWithCheckbox(event: any): void;
    onRowRightClick(event: any, rowData: any): void;
    rowDblclick(event: any, rowData: any): void;
    isSingleSelectionMode(): boolean;
    isMultipleSelectionMode(): boolean;
    findIndexInSelection(rowData: any): number;
    isSelected(rowData: any): boolean;
    equals(data1: any, data2: any): boolean;
    readonly allSelected: boolean;
    onFilterKeyup(value: any, field: any, matchMode: any): void;
    filter(value: any, field: any, matchMode: any): void;
    isFilterBlank(filter: any): boolean;
    _filter(): void;
    hasFilter(): any;
    onFilterInputClick(event: any): void;
    filterConstraints: {
        startsWith(value: any, filter: any): boolean;
        contains(value: any, filter: any): boolean;
        endsWith(value: any, filter: any): boolean;
        equals(value: any, filter: any): boolean;
        notEquals(value: any, filter: any): boolean;
        in(value: any, filter: any[]): boolean;
    };
    switchCellToEditMode(cell: any, column: Column, rowData: any): void;
    switchCellToViewMode(element: any): void;
    closeCell(): void;
    bindDocumentEditListener(): void;
    unbindDocumentEditListener(): void;
    onCellEditorKeydown(event: any, column: Column, rowData: any, rowIndex: number): void;
    onCellEditorInput(event: any, column: Column, rowData: any, rowIndex: number): void;
    onCellEditorChange(event: any, column: Column, rowData: any, rowIndex: number): void;
    onCellEditorBlur(event: any, column: Column, rowData: any, rowIndex: number): void;
    moveToPreviousCell(event: KeyboardEvent): void;
    moveToNextCell(event: KeyboardEvent): void;
    findPreviousEditableColumn(cell: Element): any;
    findNextEditableColumn(cell: Element): any;
    onCustomEditorFocusPrev(event: KeyboardEvent): void;
    onCustomEditorFocusNext(event: KeyboardEvent): void;
    findCell(element: any): any;
    initResizableColumns(): void;
    onDocumentMouseMove(event: any): void;
    onDocumentMouseUp(event: any): void;
    bindColumnResizeEvents(): void;
    unbindColumnResizeEvents(): void;
    initColumnResize(event: any): void;
    onColumnResize(event: any): void;
    onColumnResizeEnd(event: any): void;
    fixColumnWidths(): void;
    onColumnDragStart(event: any): void;
    onColumnDragover(event: any): void;
    onColumnDragleave(event: any): void;
    onColumnDrop(event: any): void;
    initColumnReordering(): void;
    findParentHeader(element: any): any;
    hasFooter(): boolean;
    isEmpty(): boolean;
    createLazyLoadMetadata(): LazyLoadEvent;
    toggleRow(row: any, event?: Event): void;
    findExpandedRowIndex(row: any): number;
    isRowExpanded(row: any): boolean;
    findExpandedRowGroupIndex(row: any): number;
    isRowGroupExpanded(row: any): boolean;
    toggleRowGroup(event: Event, row: any): void;
    reset(): void;
    exportCSV(options?: any): void;
    getBlockableElement(): HTMLElement;
    getRowStyleClass(rowData: any, rowIndex: number): string;
    visibleColumns(): Column[];
    readonly containerWidth: any;
    hasFrozenColumns(): boolean;
    ngOnDestroy(): void;
}
export declare class DataTableModule {
}
